CrossAnimationButton
====================

![View](http://7xpp4m.com1.z0.glb.clouddn.com/GIF.gif)

Usage
=====
Just add the dependency to your `build.gradle`:

```groovy
dependencies {
    compile 'com.archeryc:crossanimationbutton:1.0'
}
```

To see how the buttons are added to your xml layouts, check the sample project.

XML
=======
     <net.archeryc.crossanimationbutton.CrossAnimationButton
            android:id="@+id/crossButton"
            android:layout_width="35dp"
            android:layout_height="35dp"
            android:layout_gravity="center"
            app:cross_color="#817a7a"
            app:cross_margin="10dp"
            app:cross_width="2dp" />

Listener
=======
    crossButton.setCrossButtonStateChangeListener(new CrossAnimationButton.OnCrossButtonStateChangedListener() {
                @Override
                public void onExpanded() {
                    //todo 
                }
    
                @Override
                public void onCollapsed() {
                    //todo
                }
            });
License
=======

    Copyright (C) 2017 TokenYc

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
