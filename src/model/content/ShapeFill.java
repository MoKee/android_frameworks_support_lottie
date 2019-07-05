package frameworks.support.lottie.model.content;

import android.graphics.Path;
import androidx.annotation.Nullable;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.animation.content.Content;
import frameworks.support.lottie.animation.content.FillContent;
import frameworks.support.lottie.model.animatable.AnimatableColorValue;
import frameworks.support.lottie.model.animatable.AnimatableIntegerValue;
import frameworks.support.lottie.model.layer.BaseLayer;

public class ShapeFill implements ContentModel {
  private final boolean fillEnabled;
  private final Path.FillType fillType;
  private final String name;
  @Nullable private final AnimatableColorValue color;
  @Nullable private final AnimatableIntegerValue opacity;
  private final boolean hidden;

  public ShapeFill(String name, boolean fillEnabled, Path.FillType fillType,
                   @Nullable AnimatableColorValue color, @Nullable AnimatableIntegerValue opacity, boolean hidden) {
    this.name = name;
    this.fillEnabled = fillEnabled;
    this.fillType = fillType;
    this.color = color;
    this.opacity = opacity;
    this.hidden = hidden;
  }

  public String getName() {
    return name;
  }

  @Nullable public AnimatableColorValue getColor() {
    return color;
  }

  @Nullable public AnimatableIntegerValue getOpacity() {
    return opacity;
  }

  public Path.FillType getFillType() {
    return fillType;
  }

  public boolean isHidden() {
    return hidden;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new FillContent(drawable, layer, this);
  }

  @Override
  public String toString() {
    return "ShapeFill{" + "color=" +
        ", fillEnabled=" + fillEnabled +
        '}';
  }
}
