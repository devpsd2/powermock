package com.luc.powermock

import android.util.Log
import org.assertj.core.api.ThrowableAssert.ThrowingCallable
import org.assertj.core.internal.bytebuddy.implementation.MethodCall.call
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [Log::class], fullyQualifiedNames = ["com.luc.powermock.*"])
class ExampleUnitTest {

    @Test
    @Throws(Exception::class)
    fun mockFinalMethod() {
        val collaborator = mock(CollaboratorWithFinalMethods::class.java)
        PowerMockito.`when`(collaborator.helloMethod()).thenReturn("Hello Baeldung!")
        Assert.assertEquals(collaborator.helloMethod(), "Hello Baeldung!")
    }

    @Test
    fun mockStaticMethods() {
        mockStatic(CollaboratorWithStaticMethods::class.java)
            //mockStatic(Log::class.java)
        `when`(CollaboratorWithStaticMethods.firstMethod(anyString())).thenReturn("Hello Baeldung!")
        `when`(CollaboratorWithStaticMethods.secondMethod()).thenReturn("Nothing special")


        val firstWelcome = CollaboratorWithStaticMethods.firstMethod("Whoever")
        val secondWelcome = CollaboratorWithStaticMethods.secondMethod()

        assert("Hello Baeldung!" == firstWelcome)
        assert("Nothing special" == secondWelcome)
        assert("Nothing special" == secondWelcome)

        verifyStatic(CollaboratorWithStaticMethods::class.java, Mockito.times(1))
        CollaboratorWithStaticMethods.firstMethod(Mockito.anyString())

        verifyStatic(CollaboratorWithStaticMethods::class.java, Mockito.times(1))
        CollaboratorWithStaticMethods.secondMethod()
    }

    @Test
    @Throws(Exception::class)
    fun mockPrivateMethod() {
        val mock = mock(CollaboratorForPartialMocking::class.java)
        PowerMockito.`when`(mock.privateMethodCaller()).thenCallRealMethod()
        `when`<String>(mock, "privateMethod").thenReturn("I am a private mock method.")
        Assert.assertEquals("I am a private mock method. Welcome to the Java world.", mock.privateMethodCaller())
        verifyPrivate(mock, times(1)).invoke("privateMethod")
    }

    @Test
    fun mockVoidMethod(){
        mockStatic(CollaboratorWithStaticMethods::class.java)
        mockStatic(Log::class.java)
        CollaboratorWithStaticMethods.thirdMethod()
        verifyStatic(CollaboratorWithStaticMethods::class.java)
        CollaboratorWithStaticMethods.thirdMethod()

    }
}
