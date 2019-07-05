package frameworks.support.lottie.animation.keyframe;

import frameworks.support.lottie.value.Keyframe;
import frameworks.support.lottie.utils.MiscUtils;

import java.util.List;

public class IntegerKeyframeAnimation extends KeyframeAnimation<Integer> {

  public IntegerKeyframeAnimation(List<Keyframe<Integer>> keyframes) {
    super(keyframes);
  }

  @Override
  Integer getValue(Keyframe<Integer> keyframe, float keyframeProgress) {
    return getIntValue(keyframe, keyframeProgress);
  }

  /**
   * Optimization to avoid autoboxing.
   */
  int getIntValue(Keyframe<Integer> keyframe, float keyframeProgress) {
    if (keyframe.startValue == null || keyframe.endValue == null) {
      throw new IllegalStateException("Missing values for keyframe.");
    }

    if (valueCallback != null) {
      //noinspection ConstantConditions
      Integer value = valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame,
              keyframe.startValue, keyframe.endValue,
              keyframeProgress, getLinearCurrentKeyframeProgress(), getProgress());
      if (value != null) {
        return value;
      }
    }

    return MiscUtils.lerp(keyframe.getStartValueInt(), keyframe.getEndValueInt(), keyframeProgress);
  }

  /**
   * Optimization to avoid autoboxing.
   */
  public int getIntValue() {
    return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
  }
}
