package com.studyspringboot.ch2;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Annotation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserTest {

	@Spy
	Phone phone;

	@InjectMocks
	User user;

	@BeforeEach
	public void init(){
		user.setId(1L);
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
				return "mock call: "+invocationOnMock.getArgument(0);
			}
		}).when(phone).call(anyString());
		doReturn("mock show").when(phone).show(argThat(new ArgumentMatcher<Phone>() {
			@Override
			public boolean matches(Phone phone) {
				return true;
			}

		}));
		user.usePhone();
	}
	@Test
	@DisplayName("😅")
	public void call() {
//		assertAll("测试call",
//				()->assertEquals("call: 12345",user.callPhoneNum("12345")),
//				()->assertEquals("mock call: 12345",user.callPhoneNum("12345"))
//				);
	}

	@Test
	void getId() {
		assertEquals(1,user.getId());
	}

	@Test
	void usePhone(){
		assertEquals("use phone.",user.phone.use());
	}

	@Test
	void usePhoneTimes(){
//		assertAll("使用手机次数",
//				()->verify(phone).use(),
//				()->verify(phone,times(1)).use(),
//				()->verify(phone,times(2)).use()
//				);
	}
	@Test
	void testShow(){
		assertEquals("mock show",this.phone.show(phone));
	}
}