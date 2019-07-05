package frameworks.support.lottie.parser;


import frameworks.support.lottie.parser.moshi.JsonReader;

import java.io.IOException;

interface ValueParser<V> {
  V parse(JsonReader reader, float scale) throws IOException;
}
