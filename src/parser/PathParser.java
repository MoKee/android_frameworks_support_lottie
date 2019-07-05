package frameworks.support.lottie.parser;

import android.graphics.PointF;

import frameworks.support.lottie.parser.moshi.JsonReader;

import java.io.IOException;

public class PathParser implements ValueParser<PointF> {
  public static final PathParser INSTANCE = new PathParser();

  private PathParser() {}

  @Override public PointF parse(JsonReader reader, float scale) throws IOException {
    return JsonUtils.jsonToPoint(reader, scale);
  }
}
