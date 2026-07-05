package io.github.blockneko11.sunshinecore.event.api.handlers;

import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public final class InteractionResultHandler<T> implements InvocationHandler {
    private final List<T> handlers;

    public InteractionResultHandler(List<T> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (T handler : this.handlers) {
            Object result = method.invoke(handler, args);

            if (result != InteractionResult.PASS) {
                return result;
            }
        }

        return InteractionResult.PASS;
    }
}
