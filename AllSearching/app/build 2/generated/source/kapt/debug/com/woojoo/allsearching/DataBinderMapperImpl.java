package com.woojoo.allsearching;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.woojoo.allsearching.databinding.ActivityMainBindingImpl;
import com.woojoo.allsearching.databinding.ActivityWebViewBindingImpl;
import com.woojoo.allsearching.databinding.DialogEmptySearchingKeywordBindingImpl;
import com.woojoo.allsearching.databinding.FragmentSearchingResultBindingImpl;
import com.woojoo.allsearching.databinding.FragmentStorageBindingImpl;
import com.woojoo.allsearching.databinding.ItemImageResultBindingImpl;
import com.woojoo.allsearching.databinding.ItemStorageBindingImpl;
import com.woojoo.allsearching.databinding.ItemVideoResultBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_ACTIVITYWEBVIEW = 2;

  private static final int LAYOUT_DIALOGEMPTYSEARCHINGKEYWORD = 3;

  private static final int LAYOUT_FRAGMENTSEARCHINGRESULT = 4;

  private static final int LAYOUT_FRAGMENTSTORAGE = 5;

  private static final int LAYOUT_ITEMIMAGERESULT = 6;

  private static final int LAYOUT_ITEMSTORAGE = 7;

  private static final int LAYOUT_ITEMVIDEORESULT = 8;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.activity_web_view, LAYOUT_ACTIVITYWEBVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.dialog_empty_searching_keyword, LAYOUT_DIALOGEMPTYSEARCHINGKEYWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.fragment_searching_result, LAYOUT_FRAGMENTSEARCHINGRESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.fragment_storage, LAYOUT_FRAGMENTSTORAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.item_image_result, LAYOUT_ITEMIMAGERESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.item_storage, LAYOUT_ITEMSTORAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.woojoo.allsearching.R.layout.item_video_result, LAYOUT_ITEMVIDEORESULT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWEBVIEW: {
          if ("layout/activity_web_view_0".equals(tag)) {
            return new ActivityWebViewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_web_view is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGEMPTYSEARCHINGKEYWORD: {
          if ("layout/dialog_empty_searching_keyword_0".equals(tag)) {
            return new DialogEmptySearchingKeywordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_empty_searching_keyword is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEARCHINGRESULT: {
          if ("layout/fragment_searching_result_0".equals(tag)) {
            return new FragmentSearchingResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_searching_result is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSTORAGE: {
          if ("layout/fragment_storage_0".equals(tag)) {
            return new FragmentStorageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_storage is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMIMAGERESULT: {
          if ("layout/item_image_result_0".equals(tag)) {
            return new ItemImageResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_image_result is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSTORAGE: {
          if ("layout/item_storage_0".equals(tag)) {
            return new ItemStorageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_storage is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVIDEORESULT: {
          if ("layout/item_video_result_0".equals(tag)) {
            return new ItemVideoResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_video_result is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "callback");
      sKeys.put(2, "item");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(8);

    static {
      sKeys.put("layout/activity_main_0", com.woojoo.allsearching.R.layout.activity_main);
      sKeys.put("layout/activity_web_view_0", com.woojoo.allsearching.R.layout.activity_web_view);
      sKeys.put("layout/dialog_empty_searching_keyword_0", com.woojoo.allsearching.R.layout.dialog_empty_searching_keyword);
      sKeys.put("layout/fragment_searching_result_0", com.woojoo.allsearching.R.layout.fragment_searching_result);
      sKeys.put("layout/fragment_storage_0", com.woojoo.allsearching.R.layout.fragment_storage);
      sKeys.put("layout/item_image_result_0", com.woojoo.allsearching.R.layout.item_image_result);
      sKeys.put("layout/item_storage_0", com.woojoo.allsearching.R.layout.item_storage);
      sKeys.put("layout/item_video_result_0", com.woojoo.allsearching.R.layout.item_video_result);
    }
  }
}
