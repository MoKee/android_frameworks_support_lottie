package frameworks.support.lottie.parser;


import frameworks.support.lottie.LottieComposition;
import frameworks.support.lottie.model.animatable.AnimatableFloatValue;
import frameworks.support.lottie.model.animatable.AnimatableTransform;
import frameworks.support.lottie.model.content.Repeater;
import frameworks.support.lottie.parser.moshi.JsonReader;

import java.io.IOException;

class RepeaterParser {

  private static JsonReader.Options NAMES = JsonReader.Options.of(
      "nm",
      "c",
      "o",
      "tr",
      "hd"
  );

  private RepeaterParser() {
  }

  static Repeater parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    String name = null;
    AnimatableFloatValue copies = null;
    AnimatableFloatValue offset = null;
    AnimatableTransform transform = null;
    boolean hidden = false;

    while (reader.hasNext()) {
      switch (reader.selectName(NAMES)) {
        case 0:
          name = reader.nextString();
          break;
        case 1:
          copies = AnimatableValueParser.parseFloat(reader, composition, false);
          break;
        case 2:
          offset = AnimatableValueParser.parseFloat(reader, composition, false);
          break;
        case 3:
          transform = AnimatableTransformParser.parse(reader, composition);
          break;
        case 4:
          hidden = reader.nextBoolean();
          break;
        default:
          reader.skipValue();
      }
    }

    return new Repeater(name, copies, offset, transform, hidden);
  }
}
