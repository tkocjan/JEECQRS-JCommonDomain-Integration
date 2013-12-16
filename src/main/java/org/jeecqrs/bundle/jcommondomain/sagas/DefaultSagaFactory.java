/*
 * Copyright (c) 2013 Red Rainbow IT Solutions GmbH, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.jeecqrs.bundle.jcommondomain.sagas;

import org.jeecqrs.common.commands.CommandBus;
import org.jeecqrs.common.event.Event;
import org.jeecqrs.common.sagas.SagaTimeoutProvider;
import org.jeecqrs.sagas.Saga;
import org.jeecqrs.sagas.SagaFactory;

/**
 *
 */
public class DefaultSagaFactory implements SagaFactory<Event> {

    private final Class<? extends AbstractSaga> sagaClass;
    private final CommandBus commandBus;
    private final SagaTimeoutProvider timeoutProvider;

    public DefaultSagaFactory(
            Class<? extends AbstractSaga> sagaClass,
            CommandBus commandBus,
            SagaTimeoutProvider timeoutProvider) {

        this.sagaClass = sagaClass;
        this.commandBus = commandBus;
        this.timeoutProvider = timeoutProvider;
    }

    @Override
    public Saga<Event> createSaga(String sagaId) {
        AbstractSaga saga = SagaUtil.createInstance(sagaClass, sagaId);
        saga.setCommandBus(commandBus);
        saga.setTimeoutProvider(timeoutProvider);
        return saga;
    }

}
