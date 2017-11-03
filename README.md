# AndroidDemo

##### LoopViewPager组件在设置Adaper时，容易出现异常。现阶段设置方式：

###### （1）初始化
      ```
      private void initView(View view){
        mViewPagerBanner = (LoopViewPager) header.findViewById(R.id.screen_content);
        mViewPagerBanner.setBoundaryCaching(true);
        mViewPagerBanner.setAdapter(mBannerPagerAdapter);
        mViewPagerBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mViewIndicator.onPageSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewIndicator = (LoopIndicator) header.findViewById(R.id.viewPagerIndicator);
      }
      ```

###### （2）网络获取数据后：
      ```
      @Override
      public void onParsedResponseListener(HashMap<String, Object> stringObjectHashMap) {
          super.onParsedResponseListener(stringObjectHashMap);
          if(stringObjectHashMap == null ){
              return;
          }

          List<Banner> banners = (ArrayList<Banner>) stringObjectHashMap.get("banners");
          if(banners != null && !banners.isEmpty()){
              mBannerPagerAdapter.setData(banners);
              mViewPagerBanner.setAdapter(mBannerPagerAdapter);
              mViewIndicator.setItemCount(mBannerPagerAdapter.getCount());
          }
      }
      ```

######   预期目标：在数据发生变化后，直接调用如下代码

      Adapter.setData(List<Object> newData);
      Adapter.notifyDataSetChanged();

######   即可
