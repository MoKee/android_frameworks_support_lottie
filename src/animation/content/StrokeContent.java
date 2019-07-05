package frameworks.support.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import androidx.annotation.Nullable;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.LottieProperty;
import frameworks.support.lottie.animation.keyframe.BaseKeyframeAnimation;
import frameworks.support.lottie.animation.keyframe.ColorKeyframeAnimation;
import frameworks.support.lottie.animation.keyframe.IntegerKeyframeAnimation;
import frameworks.support.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import frameworks.support.lottie.model.content.ShapeStroke;
import frameworks.support.lottie.model.layer.BaseLayer;
import frameworks.support.lottie.value.LottieValueCallback;

import static frameworks.support.lottie.LottieProperty.STROKE_COLOR;

public class StrokeContent extends BaseStrokeContent {

  private final BaseLayer layer;
  private final String name;
  private final boolean hidden;
  private final BaseKeyframeAnimation<Integer, Integer> colorAnimation;
  @Nullable private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;

  public StrokeContent(final LottieDrawable lottieDrawable, BaseLayer layer, ShapeStroke stroke) {
    super(lottieDrawable, layer, stroke.getCapType().toPaintCap(),
        stroke.getJoinType().toPaintJoin(), stroke.getMiterLimit(), stroke.getOpacity(),
        stroke.getWidth(), stroke.getLineDashPattern(), stroke.getDashOffset());
    this.layer = layer;
    name = stroke.getName();
    hidden = stroke.isHidden();
    colorAnimation = stroke.getColor().createAnimation();
    colorAnimation.addUpdateListener(this);
    layer.addAnimation(colorAnimation);
  }

  @Override public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
    if (hidden) {
      return;
    }
    paint.setColor(((ColorKeyframeAnimation) colorAnimation).getIntValue());
    if (colorFilterAnimation != null) {
      paint.setColorFilter(colorFilterAnimation.getValue());
    }
    super.draw(canvas, parentMatrix, parentAlpha);
  }

  @Override public String getName() {
    return name;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> void addValueCallback(T property, @Nullable LottieValueCallback<T> callback) {
    super.addValueCallback(property, callback);
    if (property == STROKE_COLOR) {
      colorAnimation.setValueCallback((LottieValueCallback<Integer>) callback);
    } else if (property == LottieProperty.COLOR_FILTER) {
      if (callback == null) {
        colorFilterAnimation = null;
      } else {
        colorFilterAnimation =
            new ValueCallbackKeyframeAnimation<>((LottieValueCallback<ColorFilter>) callback);
        colorFilterAnimation.addUpdateListener(this);
        layer.addAnimation(colorAnimation);
      }
    }
  }
}
