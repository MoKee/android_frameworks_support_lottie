package frameworks.support.lottie.model.content;


import androidx.annotation.Nullable;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.animation.content.Content;
import frameworks.support.lottie.model.layer.BaseLayer;

public interface ContentModel {
  @Nullable Content toContent(LottieDrawable drawable, BaseLayer layer);
}
