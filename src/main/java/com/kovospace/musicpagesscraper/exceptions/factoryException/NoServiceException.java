package com.kovospace.musicpagesscraper.exceptions.factoryException;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;

public  class NoServiceException
        extends FactoryException
{
    @Override
    public String getMessage() {
        return "no service(s) in use yet or service missing for given platform";
    }
}
