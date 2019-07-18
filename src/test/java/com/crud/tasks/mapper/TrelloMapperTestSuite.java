package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper mapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        List<TrelloListDto> listsDto = new ArrayList<>();
        listsDto.add(new TrelloListDto("1", "Trello List", true));
        boardsDto.add(new TrelloBoardDto("1", "Test Board", listsDto));

        //When
        List<TrelloBoard> boards = mapper.mapToBoards(boardsDto);
        System.out.println(boards.get(0).getName());
        System.out.println(boards.get(0).getLists().get(0).getName());

        //Then
        assertNotNull(boards);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1", "Trello List", true));
        boards.add(new TrelloBoard("1", "Test Board", lists));

        //When
        List<TrelloBoardDto> boardsDto = mapper.mapToBoardsDto(boards);
        System.out.println(boardsDto.get(0).getName());
        System.out.println(boardsDto.get(0).getLists().get(0).getName());

        //Then
        assertNotNull(boardsDto);
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1", "Test List", true));

        //When
        List<TrelloList> list = mapper.mapToList(listDtos);

        //Then
        assertNotNull(list);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1", "Test List", true));

        //When
        List<TrelloListDto> listDto = mapper.mapToListDto(list);

        //Then
        assertNotNull(listDto);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Card", "New card", "1", "1");

        //When
        TrelloCard card = mapper.mapToCard(cardDto);

        //Then
        assertEquals("Card", card.getName());
        assertNotNull(card);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("Card", "New card", "1", "1");

        //When
        TrelloCardDto cardDto = mapper.mapToCardDto(card);

        //Then
        assertEquals("Card", cardDto.getName());
        assertNotNull(cardDto);
    }

}