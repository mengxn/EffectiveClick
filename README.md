# EffectiveClick
拦截快速点击事件

例：简单举例
```java
testBtn.setOnClickListener(new OnEffectiveClickListener() {
               @Override
               public void onEffectiveClick(View view) {
                   //do something
               }
           });
```
例：拦截1s内多次点击
```java
testBtn.setOnClickListener(new OnEffectiveClickListener(1000) {
               @Override
               public void onEffectiveClick(View view) {
                   //do something
               }
           });
```
例：获取拦截点击数
```java
testBtn.setOnClickListener(new OnEffectiveClickListener(1000) {
               @Override
               public void onEffectiveClick(View view) {
                   //do something
               }
   
               @Override
               public void onEffectiveCountClick(View view, int count) {
                   //do something
               }
           });
```