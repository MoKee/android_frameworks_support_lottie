package frameworks.support.lottie.model.content;

import androidx.annotation.Nullable;

import frameworks.support.lottie.LottieDrawable;
import frameworks.support.lottie.animation.content.Content;
import frameworks.support.lottie.animation.content.MergePathsContent;
import frameworks.support.lottie.model.layer.BaseLayer;
import frameworks.support.lottie.utils.Logger;


public class MergePaths implements ContentModel {

  public enum MergePathsMode {
    MERGE,
    ADD,
    SUBTRACT,
    INTERSECT,
    EXCLUDE_INTERSECTIONS;

    public static MergePathsMode forId(int id) {
      switch (id) {
        case 1:
          return MERGE;
        case 2:
          return ADD;
        case 3:
          return SUBTRACT;
        case 4:
          return INTERSECT;
        case 5:
          return EXCLUDE_INTERSECTIONS;
        default:
          return MERGE;
      }
    }
  }

  private final String name;
  private final MergePathsMode mode;
  private final boolean hidden;

  public MergePaths(String name, MergePathsMode mode, boolean hidden) {
    this.name = name;
    this.mode = mode;
    this.hidden = hidden;
  }

  public String getName() {
    return name;
  }

  public MergePathsMode getMode() {
    return mode;
  }

  public boolean isHidden() {
    return hidden;
  }

  @Override @Nullable public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    if (!drawable.enableMergePathsForKitKatAndAbove()) {
      Logger.warning("Animation contains merge paths but they are disabled.");
      return null;
    }
    return new MergePathsContent(this);
  }

  @Override
  public String toString() {
    return "MergePaths{" + "mode=" +  mode + '}';
  }
}
