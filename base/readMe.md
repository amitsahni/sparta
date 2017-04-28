1) Gilde library example
```
    Ex: ImageUtil.with(context, imageUrl, imageView)
                            .callback(callbackObject)
                            .imageType(ImageParam.ImageType.FILE)
                          //.imageType(ImageParam.ImageType.URI)
                          //.imageType(ImageParam.ImageType.URL)
                            .taskId(23)
                            .thumbnail(loadingTimeDefaultImage, afterErrorImage)
                            .resize(100,100)
                            .build();

    Note: context = activity, imageUrl = path of image,imageView = ImageView where you want to put downlaoded image,
          loadingTimeDefaultImage = R.drawable.loading_image, afterErrorImage = R.drawable.error_image
```

2) ActivityManager example
```
    2.1) Activity open sample:
         Ex: ActivityManager.with(this).activityType(ActivityParam.ActivityType.START)
                                         .bundle(bundleObj)
                                         .klass(NextActivity.class).build();

    Note: ActivityParam.ActivityType.START = to start next activity
          ActivityParam.ActivityType.START_FINISH = to satrt next activity and destroy current activity

    2.2) Destroy current activity :
         Ex: ActivityManager.with(this).activityType(ActivityParam.ActivityType.FINISH).build();
```

3) FragmentManager example
```
    3.1) Replace fragment with activity or fragment sample:
         Ex: FragmentManager.with(this, replaceId)
                             .fragment(NextFragment.init(NextFragment.class, getBundle()))
                             .backStack(false)
                             .build();

         Note: replaceId = R.id.root_layout
               NextFragment = Fragment that you want to add
               getBundle = inbuild method of library that return Bundle object(bundle data passed from previous frgment or activity)
               backstack = boolean value

    3.2) Pop fragment from activity or fragment sample:
         Ex: FragmentManager.with(this, replaceId)
                            .type(FragParam.FragType.POP)
                            .build();
```



