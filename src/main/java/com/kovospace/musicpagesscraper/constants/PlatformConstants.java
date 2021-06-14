package com.kovospace.musicpagesscraper.constants;

import com.kovospace.musicpagesscraper.interfaces.Platform;
import java.util.HashMap;

public class PlatformConstants {

  public static final HashMap<String, Platform> PLATFORM_INFOS = new HashMap<String, Platform>() {{
    put("bandzone", new Platform() {
      @Override public String getSlug() { return "bandzone"; }
      @Override public String getDescription() { return "Bandzone.cz je portál združujúci všetky české a slovenské kapely, od tých známejších po alternatívnu scénu a underground."; }
      @Override public String getName() { return "Bandzone.cz"; }
      @Override public String getImageUrl() { return null; }
      @Override public String getClassName() { return "Bandzone"; }
    });
    put("freeteknomusic", new Platform() {
      @Override public String getSlug() { return "freeteknomusic"; }
      @Override public String getDescription() { return "FreeTeknoMusic.org je globálny portál soundsystems & DIY scény, ktorá produkuje hlavne tvrdšie štýly elektronickej hudby, predovšetkým freetekno/tribecore/acid/breakbeat/drum&bass a pod."; }
      @Override public String getName() { return "FreeTeknoMusic.org"; }
      @Override public String getImageUrl() { return null; }
      @Override public String getClassName() { return "FreeTeknoMusic"; }
    });
  }};

}
