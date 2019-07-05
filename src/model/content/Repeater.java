package frameworks.support.lottie.model.content;

import androidx.annotation.Nullable;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.animation.content.Content;
import frameworks.support.lottie.animation.content.RepeaterContent;
import frameworks.support.lottie.model.animatable.AnimatableFloatValue;
import frameworks.support.lottie.model.animatable.AnimatableTransform;
import frameworks.support.lottie.model.layer.BaseLayer;

public class Repeater implements ContentModel {
  private final String name;
  private final AnimatableFloatValue copies;
  private final AnimatableFloatValue offset;
  private final AnimatableTransform transform;
  private final boolean hidden;

  public Repeater(String name, AnimatableFloatValue copies, AnimatableFloatValue offset,
                  AnimatableTransform transform, boolean hidden) {
    this.name = name;
    this.copies = copies;
    this.offset = offset;
    this.transform = transform;
    this.hidden = hidden;
  }

  public String getName() {
    return name;
  }

  public AnimatableFloatValue getCopies() {
    return copies;
  }

  public AnimatableFloatValue getOffset() {
    return offset;
  }

  public AnimatableTransform getTransform() {
    return transform;
  }

  public boolean isHidden() {
    return hidden;
  }

  @Nullable @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new RepeaterContent(drawable, layer, this);
  }
}
