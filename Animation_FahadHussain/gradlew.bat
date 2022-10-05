<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/edtName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:inputType="textPersonName"
        android:ems="10"
        android:hint="Enter Name" />

    <EditText
        android:id="@+id/edtPrice"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:inputType="textPersonName"
        android:ems="10"
        android:hint="Enter Price" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="150dp"
        android:layout_height="150dp"
        app:srcCompat="@mipmap/ic_launcher" />

<LinearLayout
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal">
    <Button
        android:id="@+id/btnChoose"
        android:text="Choose image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btnAdd"
        android:text="Add"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
 />

    <Button
        android:id="@+id/btnList"
        android:text="View"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>
</LinearLayout>                                                                                                                                                                                                                                                                                                                                                                                                                                           