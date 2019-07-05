package frameworks.support.lottie.parser;


import frameworks.support.lottie.LottieComposition;
import frameworks.support.lottie.model.animatable.AnimatableShapeValue;
import frameworks.support.lottie.model.content.ShapePath;
import frameworks.support.lottie.parser.moshi.JsonReader;

import java.io.IOException;

class ShapePathParser {

  static JsonReader.Options NAMES = JsonReader.Options.of(
      "nm",
      "ind",
      "ks",
      "hd"
  );

  private ShapePathParser() {
  }

  static ShapePath parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    String name = null;
    int ind = 0;
    AnimatableShapeValue shape = null;
    boolean hidden = false;

    while (reader.hasNext()) {
      switch (reader.selectName(NAMES)) {
        case 0:
          name = reader.nextString();
          break;
        case 1:
          ind = reader.nextInt();
          break;
        case 2:
          shape = AnimatableValueParser.parseShapeData(reader, composition);
          break;
        case 3:
          hidden = reader.nextBoolean();
          break;
        default:
          reader.skipValue();
      }
    }

    return new ShapePath(name, ind, shape, hidden);
  }
}
