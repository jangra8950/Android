@file:Suppress("DEPRECATION")

package com.example.rewards


import android.Manifest
import android.R.attr
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.firstproject.BuildConfig
import com.example.firstproject.R
import com.example.firstproject.databinding.GallaryDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.File
import java.util.Calendar


class SignUpActivity : AppCompatActivity() {

    private val numbers = arrayOf("+91", "+95", "+72", "+65", "+76")
    private lateinit var num: AutoCompleteTextView
    private lateinit var dob: TextInputEditText
    private lateinit var phone: EditText
    private lateinit var button: Button
    private lateinit var image: ImageView


    // private lateinit var image: CropImageView
    private val gal = 1
    private val cam = 2

    val storage = 200
    var storagePermission: Array<String>? = null
    var resultUri: Uri? = null


    private var latestTmpUri: Uri? = null
//
//   private val selectImageGallery=registerForActivityResult(ActivityResultContracts.GetContent()){
//
//        image.setImageURI( it)                                // only take
//    }
//
//    private val selectImageCamera=registerForActivityResult(ActivityResultContracts.TakePicture()){
//        if(it) {
//
//            image.setImageURI(latestTmpUri)                           // only take
//        }
//    }

//    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
//        if (result.isSuccessful) {
//            // use the returned uri
//            image.setImageURI(Uri.parse(result.getUriFilePath(this, true)))         //  from library
//
//        } else {
//            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//        }
//    }
//    private val cropImageCameraOptions = options {
//        setGuidelines(CropImageView.Guidelines.ON)                              // from library
//        setImageSource(includeGallery = false, includeCamera = true)
//    }
//    private val cropImageGallaryOptions = options {
//        setGuidelines(CropImageView.Guidelines.ON)                                //from library
//        setImageSource(includeGallery = true, includeCamera = false)
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        image = findViewById(R.id.imageicon)
        button = findViewById(R.id.btnSign)
        dob = findViewById(R.id.dob)
        num = findViewById(R.id.outlined_exposed_dropdown_editable)
        phone = findViewById(R.id.ph)

        storagePermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)


        image.setOnClickListener {
            gallaryDialog()
            //showDialog()
          //  pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }




        button.setOnClickListener {
            val number = phone.text.toString()
            val intent = Intent(this, OtpActivity::class.java)
            intent.putExtra("key", number)
            startActivity(intent)
        }
        dob.setOnClickListener {
            val c = Calendar.getInstance()
            val years = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dob.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                years,
                month,
                day
            )
            datePickerDialog.show()
        }
        num.run {
            setOnClickListener {
                setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        numbers
                    )
                )
            }
            inputType = InputType.TYPE_NULL
            performClick()

        }

    }

//    private val pickImageContract=registerForActivityResult(ActivityResultContracts.GetContent()){
//        startCrop( it)                                       // take and crop
//    }

    private val takePhotoContract=registerForActivityResult(ActivityResultContracts.TakePicture()){
        if(it)
            startCrop(latestTmpUri)                               // take and crop
    }

    private val pickMedia=registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        if(it!=null)
          startCrop(it)
    }


    private fun gallaryDialog() {
        val dialogOption = BottomSheetDialog(this)
        val view = GallaryDialogLayoutBinding.inflate(LayoutInflater.from(this))
        dialogOption.setCancelable(false)
        dialogOption.setContentView(view.root)
        view.tvGallary.setOnClickListener {

            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
           // pickImageContract()                                  // take and crop
          //  cropImage.launch(cropImageGallaryOptions)           //  from library

            dialogOption.dismiss()
        }
        view.tvCamera.setOnClickListener {
            takeImage()
            //cropImage.launch(cropImageCameraOptions)           //    from library
            dialogOption.dismiss()
        }
        dialogOption.show()
    }

    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takePhotoContract.launch(uri)
            }
        }
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(applicationContext, "${BuildConfig.APPLICATION_ID}.provider", tmpFile)
    }

//    private fun captureImageContract(uri: Uri) {
//        takePhotoContract.launch(uri)
//    }

//    private fun pickImageContract() {
//        pickImageContract.launch("image/*")
//    }

//    private fun createImageURI(): Uri? {
//
//        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
//            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        else
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//
//        val imageName = System.currentTimeMillis()
//
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, "$imageName")
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//        }
//
//        val finalURI = contentResolver.insert(imageCollection, contentValues)
//        resultUri = finalURI
//        return finalURI
//    }

//    private fun refreshGallery() {
//        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
//            mediaScanIntent.data = resultUri
//            sendBroadcast(mediaScanIntent)
//        }
//    }

//    private fun loadImage(uri: Uri?) {                                     // to set image in view
//        Glide.with(applicationContext).asBitmap().load(uri)
//            .into(object : CustomTarget<Bitmap>(image.width, image.height) {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap>?
//                ) {
//                    image.setImageBitmap(resource)
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    //
//                }
//            })
//        refreshGallery()
//    }



//    private fun showDialog()
//    {
//        try {
//            val imageItems = arrayOf<CharSequence>(
//                getString(R.string.take_picture),
//                getString(R.string.choose_from_gallery),
//                getString(R.string.cancel)
//            )
//
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle(getString(R.string.select_one))
//
//            builder.setItems(imageItems) { dialog, item ->
//                if (imageItems[item] == getString(R.string.take_picture)) {
//                    dialog.dismiss()
//
//                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
//                        putExtra(MediaStore.EXTRA_OUTPUT, "uri")
//                    }
//                    startActivityForResult(intent, 10)
//                        createImageURI()?.let { uri ->
//                            captureImageContract(uri)
//                        }
//
//                } else if (imageItems[item] == getString(R.string.choose_from_gallery)) {
//                    dialog.dismiss()
//                  //  pickImageContract()
//                } else if (imageItems[item] == getString(R.string.cancel)) {
//                    dialog.dismiss()
//                }
//            }
//            builder.show()
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//
//    }


    private fun startCrop(imageUri: Uri?) {
        CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
            .start(this);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode === RESULT_OK) {
                val resultUri = result.uri
                image.setImageURI(resultUri)
            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }


}


  //  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
   //     super.onActivityResult(requestCode, resultCode, data)

  //      if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE ) {

     //       val uri = CropImage.getPickImageResultUri(this, data)
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, uri)) {
////                uri = imageUri
//
//                // request permissions and handle the result in onRequestPermissionsResult()
//                requestPermissions(
//                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
//                    0
//                )
////                startCrop(uri)
//            }
//            else
          //      cropImage.launch(cropImageCameraOptions)
    //    }
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && data?.data !=null) {
//            val result: CropImage.ActivityResult = CropImage.getActivityResult(data)
//            if (resultCode == RESULT_OK) {
//                image.setImageURI(result.uri)
//               // image.setImageUriAsync(result.uri)
//            }
//        }
   // }

//    private fun startCrop(imageUri: Uri?) {
//        CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
//            .start(this);
//    }


