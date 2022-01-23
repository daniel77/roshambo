package com.ciklum.roshambo;

import com.ciklum.roshambo.model.Result;
import com.ciklum.roshambo.model.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class RoshamboApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private String gameUuid;

	@BeforeEach
	@SneakyThrows
	public void createNewGame(){
		MockHttpServletResponse response = performGet("/start");
		gameUuid = response.getContentAsString();
		assertNotNull(gameUuid);
	}

	@Test
	@SneakyThrows
	public void testGameRulesRound() throws Exception {
		MockHttpServletResponse response = performGet("/"+gameUuid+"/round");
		String resultResponse = response.getContentAsString();
		assertEquals(Shape.ROCK.name(), objectMapper.readTree(resultResponse).get("shapeP1").asText());

		String shapeP2 = objectMapper.readTree(resultResponse).get("shapeP2").asText();
		String result = objectMapper.readTree(resultResponse).get("result").asText();
		if (shapeP2.equals(Shape.SCISSORS.name())) {
			assertEquals(Result.P1_WINS.getFriendlyName(), result);
		} else if (shapeP2.equals(Shape.ROCK.name())) {
			assertEquals(Result.DRAW.getFriendlyName(), result);
		} else {
			assertEquals(Result.P2_WINS.getFriendlyName(), result);
		}
	}

	@Test
	@SneakyThrows
	public void testInvalidUiidRound() {
		MockHttpServletResponse response = performGet("/invaliduids/round");
		assertEquals("500", objectMapper.readTree(response.getContentAsString()).get("status").asText());
		assertTrue(objectMapper.readTree(response.getContentAsString()).get("message").asText().contains("Game not found") );
	}

	@Test
	@SneakyThrows
	public void testGameCountRounds() {
		int totalCalls = 5;
		IntStream.rangeClosed(1, totalCalls)
				.forEach(value -> performGet("/"+gameUuid+"/round"));
		MockHttpServletResponse response = performGet("/"+gameUuid+"/rounds");
		assertEquals(String.valueOf(totalCalls), objectMapper.readTree(response.getContentAsString()).asText());
	}

	@SneakyThrows
	@Test
	public void testInvalidUiidCountRounds() {
		MockHttpServletResponse response = performGet("/invaliduids/rounds");
		assertEquals("500", objectMapper.readTree(response.getContentAsString()).get("status").asText());
		assertTrue(objectMapper.readTree(response.getContentAsString()).get("message").asText().contains("Game not found") );
	}

	@SneakyThrows
	@Test
	public void testGameRestart() {
		IntStream.rangeClosed(0, 4)
				.forEach(value -> performGet("/"+gameUuid+"/round"));
		performGet("/"+gameUuid+"/restart");
		MockHttpServletResponse response = performGet("/"+gameUuid+"/rounds");
		assertEquals(String.valueOf(0), objectMapper.readTree(response.getContentAsString()).asText());
	}

	@SneakyThrows
	@Test
	public void testInvalidUiidRestart() {
		MockHttpServletResponse response = performGet("/invaliduids/restart");
		assertEquals("500", objectMapper.readTree(response.getContentAsString()).get("status").asText());
		assertTrue(objectMapper.readTree(response.getContentAsString()).get("message").asText().contains("Game not found") );
	}

	@SneakyThrows
	private MockHttpServletResponse performGet(String entryPoint) {
		return mockMvc.perform(get(entryPoint)
				.content("")).andReturn().getResponse();
	}
}
