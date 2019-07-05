package frameworks.support.lottie.parser;


import frameworks.support.lottie.LottieComposition;
import frameworks.support.lottie.model.content.ContentModel;
import frameworks.support.lottie.model.content.ShapeGroup;
import frameworks.support.lottie.parser.moshi.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ShapeGroupParser {

  private ShapeGroupParser() {}
  private static JsonReader.Options NAMES = JsonReader.Options.of(
      "nm",
      "hd",
      "it"
  );
  static ShapeGroup parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    String name = null;
    boolean hidden = false;
    List<ContentModel> items = new ArrayList<>();

    while (reader.hasNext()) {
      switch (reader.selectName(NAMES)) {
        case 0:
          name = reader.nextString();
          break;
        case 1:
          hidden = reader.nextBoolean();
          break;
        case 2:
          reader.beginArray();
          while (reader.hasNext()) {
            ContentModel newItem = ContentModelParser.parse(reader, composition);
            if (newItem != null) {
              items.add(newItem);
            }
          }
          reader.endArray();
          break;
        default:
          reader.skipValue();
      }
    }

    return new ShapeGroup(name, items, hidden);
  }
}
