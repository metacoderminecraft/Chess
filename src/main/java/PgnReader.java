import org.antlr.v4.runtime.misc.NotNull;

public class PgnReader extends PGNBaseListener {

  private String white = null;
  private String black = null;

  @Override
  public void enterTag_pair(@NotNull PGNParser.Tag_pairContext ctx) {

    String tagName = ctx.tag_name().getText();

    if(tagName.equals("White")) {
      white = ctx.tag_value().getText().replace("\"", "");
    }
    else if(tagName.equals("Black")) {
      black = ctx.tag_value().getText().replace("\"", "");
    }
  }

  // Grammar production rule:
  //
  //   movetext_section
  //    : element_sequence game_termination
  //    ;
  @Override
  public void enterMovetext_section(@NotNull PGNParser.Movetext_sectionContext ctx) {

    String result = ctx.game_termination().getText();

    System.out.printf("`%s` with white against `%s` with black resulted in: %s\n",
        white, black, result);
  }
}