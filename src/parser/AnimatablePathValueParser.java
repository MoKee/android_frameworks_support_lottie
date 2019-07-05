package frameworks.support.lottie.parser;

import android.graphics.PointF;
import android.util.JsonToken;

import frameworks.support.lottie.LottieComposition;
import frameworks.support.lottie.parser.moshi.JsonReader;
import frameworks.support.lottie.value.Keyframe;
import frameworks.support.lottie.model.animatable.AnimatableFloatValue;
import frameworks.support.lottie.model.animatable.AnimatablePathValue;
import frameworks.support.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import frameworks.support.lottie.model.animatable.AnimatableValue;
import frameworks.support.lottie.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimatablePathValueParser {

  private static JsonReader.Options NAMES = JsonReader.Options.of(
      "k",
      "x",
      "y"
  );

  private AnimatablePathValueParser() {}

  public static AnimatablePathValue parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    List<Keyframe<PointF>> keyframes = new ArrayList<>();
    if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
      reader.beginArray();
      while (reader.hasNext()) {
        keyframes.add(PathKeyframeParser.parse(reader, composition));
      }
      reader.endArray();
      KeyframesParser.setEndFrames(keyframes);
    } else {
      keyframes.add(new Keyframe<>(JsonUtils.jsonToPoint(reader, Utils.dpScale())));
    }
    return new AnimatablePathValue(keyframes);
  }

  /**
   * Returns either an {@link AnimatablePathValue} or an {@link AnimatableSplitDimensionPathValue}.
   */
  static AnimatableValue<PointF, PointF> parseSplitPath(
      JsonReader reader, LottieComposition composition) throws IOException {

    AnimatablePathValue pathAnimation = null;
    AnimatableFloatValue xAnimation = null;
    AnimatableFloatValue yAnimation = null;

    boolean hasExpressions = false;

    reader.beginObject();
    while (reader.peek() != JsonReader.Token.END_OBJECT) {
      switch (reader.selectName(NAMES)) {
        case 0:
          pathAnimation = AnimatablePathValueParser.parse(reader, composition);
          break;
        case 1:
          if (reader.peek() == JsonReader.Token.STRING) {
            hasExpressions = true;
            reader.skipValue();
          } else {
            xAnimation = AnimatableValueParser.parseFloat(reader, composition);
          }
          break;
        case 2:
          if (reader.peek() == JsonReader.Token.STRING) {
            hasExpressions = true;
            reader.skipValue();
          } else {
            yAnimation = AnimatableValueParser.parseFloat(reader, composition);
          }
          break;
        default:
          reader.skipName();
          reader.skipValue();
      }
    }
    reader.endObject();

    if (hasExpressions) {
      composition.addWarning("Lottie doesn't support expressions.");
    }

    if (pathAnimation != null) {
      return pathAnimation;
    }
    return new AnimatableSplitDimensionPathValue(xAnimation, yAnimation);
  }

}
