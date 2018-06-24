package com.ogbrown.utility.cache;

import java.util.Properties;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.CacheDecoratorFactory;
import net.sf.ehcache.constructs.EhcacheDecoratorAdapter;

public class CustomEhcacheDecoratorFactory extends CacheDecoratorFactory {

    public Ehcache createDecoratedEhcache(final Ehcache cache,
            final Properties properties) {
        return new EhcacheDecoratorAdapter(cache) {
            private final String name = properties.getProperty("name");

            public String getName() {
                return name;
            }
        };
    }

    public Ehcache createDefaultDecoratedEhcache(final Ehcache cache,
            final Properties properties) {
        return new EhcacheDecoratorAdapter(cache) {
            private final String name = properties.getProperty("name");

            public String getName() {
                return name;
            }
        };
    }
}