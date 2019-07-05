package frameworks.support.lottie.parser;

import android.graphics.PointF;
import android.util.JsonToken;

import frameworks.support.lottie.LottieComposition;
import frameworks.support.lottie.parser.moshi.JsonReader;
import frameworks.support.lottie.value.Keyframe;
import frameworks.support.lottie.animation.keyframe.PathKeyframe;
import frameworks.support.lottie.utils.Utils;

import java.io.IOException;

class PathKeyframeParser {

  private PathKeyframeParser() {}

  static PathKeyframe parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    boolean animated = reader.peek() == JsonReader.Token.BEGIN_OBJECT;
    Keyframe<PointF> keyframe = KeyframeParser.parse(
        reader, composition, Utils.dpScale(), PathParser.INSTANCE, animated);

    return new PathKeyframe(composition, keyframe);
  }
}
