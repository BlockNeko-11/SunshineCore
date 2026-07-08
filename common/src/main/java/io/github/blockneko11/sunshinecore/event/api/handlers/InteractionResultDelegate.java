package io.github.blockneko11.sunshinecore.event.api.handlers;

import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public final class InteractionResultDelegate<T> extends EventDelegate<T> {
    public InteractionResultDelegate(List<T> handlers) {
        super(handlers);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InteractionResult finalResult = InteractionResult.PASS;

        for (T handler : this.handlers) {
            Object result = method.invoke(handler, args);

            if (result != InteractionResult.PASS) {
                finalResult  = (InteractionResult) result;
            }
        }

        return finalResult;
    }
}
