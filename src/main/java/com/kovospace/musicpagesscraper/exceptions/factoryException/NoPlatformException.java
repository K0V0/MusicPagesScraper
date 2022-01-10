package com.kovospace.musicpagesscraper.exceptions.factoryException;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;

public  class NoPlatformException
        extends FactoryException
{
  @Override
  public String getMessage() {
    return "no given platform exist";
  }
}
