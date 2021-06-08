package com.kovospace.musicpagesscraper.repositories;

import com.kovospace.musicpagesscraper.interfaces.Platform;
import java.util.List;

public interface PlatformRepository {

  List<Platform> getAll();

}
