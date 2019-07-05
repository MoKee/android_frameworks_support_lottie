package frameworks.support.lottie.model.animatable;

import frameworks.support.lottie.animation.keyframe.BaseKeyframeAnimation;
import frameworks.support.lottie.value.Keyframe;

import java.util.List;

public interface AnimatableValue<K, A> {
  List<Keyframe<K>> getKeyframes();
  boolean isStatic();
  BaseKeyframeAnimation<K, A> createAnimation();
}
