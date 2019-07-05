package frameworks.support.lottie.model.content;

import android.graphics.PointF;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.animation.content.Content;
import frameworks.support.lottie.animation.content.RectangleContent;
import frameworks.support.lottie.model.animatable.AnimatableFloatValue;
import frameworks.support.lottie.model.animatable.AnimatablePointValue;
import frameworks.support.lottie.model.animatable.AnimatableValue;
import frameworks.support.lottie.model.layer.BaseLayer;

public class RectangleShape implements ContentModel {
  private final String name;
  private final AnimatableValue<PointF, PointF> position;
  private final AnimatablePointValue size;
  private final AnimatableFloatValue cornerRadius;
  private final boolean hidden;

  public RectangleShape(String name, AnimatableValue<PointF, PointF> position,
                        AnimatablePointValue size, AnimatableFloatValue cornerRadius, boolean hidden) {
    this.name = name;
    this.position = position;
    this.size = size;
    this.cornerRadius = cornerRadius;
    this.hidden = hidden;
  }

  public String getName() {
    return name;
  }

  public AnimatableFloatValue getCornerRadius() {
    return cornerRadius;
  }

  public AnimatablePointValue getSize() {
    return size;
  }

  public AnimatableValue<PointF, PointF> getPosition() {
    return position;
  }

  public boolean isHidden() {
    return hidden;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new RectangleContent(drawable, layer, this);
  }

  @Override public String toString() {
    return "RectangleShape{position=" + position +
        ", size=" + size +
        '}';
  }
}
