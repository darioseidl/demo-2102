package com.example.demo2056

import CustomDomainObjectReader
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.data.mapping.context.PersistentEntities
import org.springframework.data.rest.core.support.EntityLookup
import org.springframework.data.rest.webmvc.config.PersistentEntityResourceHandlerMethodArgumentResolver
import org.springframework.data.rest.webmvc.config.RootResourceInformationHandlerMethodArgumentResolver
import org.springframework.data.rest.webmvc.json.DomainObjectReader
import org.springframework.data.rest.webmvc.mapping.Associations
import org.springframework.data.rest.webmvc.support.BackendIdHandlerMethodArgumentResolver
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.plugin.core.PluginRegistry

/**
 * Replace the [DomainObjectReader] with the [CustomDomainObjectReader].
 */
class DomainObjectReaderCustomizer : BeanPostProcessor {

    @Suppress("UNCHECKED_CAST")
    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (beanName == "persistentEntityArgumentResolver") {
            val resolver = bean as PersistentEntityResourceHandlerMethodArgumentResolver
            val reader = resolver.getPrivateField("reader") as DomainObjectReader

            return PersistentEntityResourceHandlerMethodArgumentResolver(
                resolver.getPrivateField("messageConverters") as List<HttpMessageConverter<*>>,
                resolver.getPrivateField("resourceInformationResolver") as RootResourceInformationHandlerMethodArgumentResolver,
                resolver.getPrivateField("idResolver") as BackendIdHandlerMethodArgumentResolver,
                CustomDomainObjectReader(
                    reader.getPrivateField("entities") as PersistentEntities,
                    reader.getPrivateField("associationLinks") as Associations,
                ),
                resolver.getPrivateField("lookups") as PluginRegistry<EntityLookup<*>, Class<*>>
            )
        }
        return bean
    }

}

fun Any.getPrivateField(fieldName: String): Any? =
    javaClass.getDeclaredField(fieldName).let {
        it.isAccessible = true
        it.get(this)
    }

fun Any.setPrivateField(fieldName: String, value: Any) {
    javaClass.getDeclaredField(fieldName).let {
        it.isAccessible = true
        it.set(this, value)
    }
}

