package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.Club;
import com.example.smilekarina.club.domain.ClubList;
import com.example.smilekarina.club.domain.ClubType;
import com.example.smilekarina.club.infrastructure.ClubListRepository;
import com.example.smilekarina.club.infrastructure.ClubRepository;
import com.example.smilekarina.club.vo.BizIn;
import com.example.smilekarina.club.vo.CarIn;
import com.example.smilekarina.club.vo.MomKidsIn;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClubServiceImpl implements ClubService{
    private final UserService userService;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final ClubListRepository clubListRepository;

    @Transactional
    @Override
    public void registerClubForMemberKids(String token, MomKidsIn momKidsIn) {
        Long userId = userService.getUserIdFromToken(token);
        String commaSeparatedString = transformToCommaSeparatedString(momKidsIn);
        Long clubId = createClub(ClubType.MOMKIDS,commaSeparatedString);
        createClubList(userId, clubId);
    }

    @Override
    public void registerClubForMemberBeauty(String token) {
        Long userId = userService.getUserIdFromToken(token);
        Long clubId = createClub(ClubType.BEAUTY,null);
        createClubList(userId, clubId);
    }

    @Override
    public void registerClubForCar(String token, CarIn carIn) {
        Long userId = userService.getUserIdFromToken(token);
        String commaSeparatedString = transformToCommaSeparatedString(carIn);
        Long clubId = createClub(ClubType.CAR,commaSeparatedString);
        createClubList(userId, clubId);
    }

    @Override
    public void registerClubForBiz(String token, BizIn bizIn) {
        Long userId = userService.getUserIdFromToken(token);
        System.out.println(userId);
        String commaSeparatedString = transformToCommaSeparatedString(bizIn);
        System.out.println(commaSeparatedString);
        Long clubId = createClub(ClubType.BIZ,commaSeparatedString);
        createClubList(userId, clubId);
    }

    @Override
    public Long createClub(ClubType clubType,String content) {
        Club club = Club.builder()
                .clubType(clubType)
                .clubContent(content)
                .build();
        Club savedClub = clubRepository.save(club);
        return savedClub.getId();
    }

    @Override
    public void createClubList(Long userId, Long clubId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("Club not found with ID: " + clubId));
        log.info("엔티티 들고왔다");
        ClubList clubList = ClubList.builder()
                .user(user)
                .club(club)
                .build();
        log.info("클럽 만들었다 : {} {}",clubList.getUser(), clubList.getClub());
        clubListRepository.save(clubList);
        log.info("저장했다");
    }

    @Override
    public void clear(String token, ClubType clubType) {
        Long userId = userService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        List<ClubList> clubLists = clubListRepository.findByUserAndClub_ClubType(user, clubType);
        if (!clubLists.isEmpty()) {
            clubListRepository.deleteAll(clubLists); // 일치하는 모든 ClubList 항목을 삭제합니다
        }
    }

    private String transformToCommaSeparatedString(Object obj) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        log.info("여긴 왔다냥 {}",fields);
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                sb.append(value != null ? value.toString() : " ");
                sb.append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // remove the last comma
        }
        return sb.toString();
    }

}
