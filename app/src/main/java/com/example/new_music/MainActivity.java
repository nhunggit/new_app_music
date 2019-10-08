package com.example.new_music;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,MyService.class);
        startService(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tuychon_menu,menu);
        MenuItem search=menu.findItem(R.id.timkiem);
        SearchView searchView=(SearchView)search.getActionView();
        return super.onCreateOptionsMenu(menu);
    }
}
//       Fragment fragmentlistBaihat = new fragmentListBaihat();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist, fragmentlistBaihat).commit();
//        //contentProvider contentProvider=new contentProvider();
//        //contentProvider.onCreate();
////        Fragment fragmentbaihat=new fragmentBaihat();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentbaihat,fragmentbaihat).commit();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlistbaihat, fragmentlistBaihat).commit();
//    }
////
////    public void onClickbh(View view) {
////        Fragment fragmentBaihat=new fragmentBaihat();
////        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragmentBaihat).commit();
////    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.tuychon_menu,menu);
//        MenuItem search=menu.findItem(R.id.timkiem);
//        SearchView searchView= (SearchView) search.getActionView();
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        return super.onContextItemSelected(item);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void tenbaihat(View view) {
//        Fragment fragment=new fragmentBaihat();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlist,fragment).commit();

 //   }
//    public void provider(){
//        ContentResolver contentResolver=getContentResolver();
//        Cursor cusor=contentResolver.query(nhacUri,null,null,null,null);
//        cusor.moveToFirst();
//        int i=0;
//        while(!cusor.isAfterLast()){
//            String duongdanAudio=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.DATA));
//            //int bia=Integer.parseInt(duongdanAudio);
//            String ten=cusor.getString(cusor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//            //baiHat baiHat=new baiHat(i,ten,bia);
//            Log.d("giatri",ten+"___"+i+"__"+duongdanAudio);
//            cusor.moveToNext();
//            i++;
//        }
//    }
//}
