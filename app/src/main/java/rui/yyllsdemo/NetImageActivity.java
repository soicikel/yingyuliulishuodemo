package rui.yyllsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.displayingbitmaps.util.ImageCache;
import com.example.android.displayingbitmaps.util.ImageFetcher;

/**
 * Created by rui on 15/3/10.
 */
public class NetImageActivity extends ActionBarActivity{

    private static final String[] IMAGES = {
    "http://llss.qiniudn.com/forum/image/525d1960c008906923000001_1397820588.jpg",
    "http://llss.qiniudn.com/forum/image/e8275adbeedc48fe9c13cd0efacbabdd_1397877461243.jpg",
    "http://llss.qiniudn.com/uploads/forum/topic/attached_img/5350db2ffcfff258b500dcb2/_____2014-04-18___3.52.33.png"};

    private ListView mListView;
    private ImageFetcher mImageFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_netimage);

        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(this, "images");
        mImageFetcher = new ImageFetcher(this, Integer.MAX_VALUE);
        mImageFetcher.addImageCache(getSupportFragmentManager(), cacheParams);
        mImageFetcher.setImageFadeIn(false);

        mListView = (ListView) findViewById(R.id.netimage_list);
        mListView.setDividerHeight(0);
        mListView.setAdapter(new ListAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImageFetcher.setExitTasksEarly(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mImageFetcher.setExitTasksEarly(true);
        mImageFetcher.flushCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImageFetcher.closeCache();
    }

    private class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String picUrl = IMAGES[position];
            if (null == convertView) {
                convertView = LayoutInflater.from(NetImageActivity.this).inflate(R.layout.layout_netimage, parent, false);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
                convertView.setTag(imageView);
            }
            ImageView image = (ImageView) convertView.getTag();
            mImageFetcher.loadImage(picUrl, image);
            return convertView;
        }
    }
}
