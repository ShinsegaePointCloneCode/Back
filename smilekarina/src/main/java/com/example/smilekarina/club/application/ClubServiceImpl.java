package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.*;
import com.example.smilekarina.club.infrastructure.ClubListRepository;
import com.example.smilekarina.club.infrastructure.ClubRepository;
import com.example.smilekarina.club.vo.*;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
//@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ClubServiceImpl implements ClubService{
    private final UserService userService;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final ClubListRepository clubListRepository;
    private final JPAQueryFactory query;
    @Override
    public void registerClubForMomKids(String token, MomKidsIn momKidsIn) {
        Long userId = getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        // todo : front 토큰과 uuid로 받아올 것 변경예정
        String commaSeparatedString = transformToCommaSeparatedString(momKidsIn);
        Optional<ClubList> clubList = clubListRepository.findFirstByUserAndClub_ClubType(user,ClubType.MOMKIDS);
        if (clubList.isEmpty()) {
            Long clubId = createClub(ClubType.MOMKIDS,commaSeparatedString);
            createClubList(userId, clubId);
        } else {
            modifyClub(clubList.get().getClub(), commaSeparatedString);
        }
    }

    @Override
    public void registerClubForBeauty(String token) {
        // todo : front 토큰과 uuid로 받아올 것 변경예정
        Long userId = getUserIdFromToken(token);
        Long clubId = createClub(ClubType.BEAUTY,null);
        createClubList(userId, clubId);
    }

    @Override
    public void registerClubForCar(String token, CarIn carIn) {
        // todo : front 토큰과 uuid로 받아올 것 변경예정
        Long userId = getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        String commaSeparatedString = transformToCommaSeparatedString(carIn);
        Optional<ClubList> clubList = clubListRepository.findFirstByUserAndClub_ClubType(user,ClubType.CAR);
        if (clubList.isEmpty()) {
            Long clubId = createClub(ClubType.CAR,commaSeparatedString);
            createClubList(userId, clubId);
        } else {
            modifyClub(clubList.get().getClub(), commaSeparatedString);
        }
    }

    @Override
    public void registerClubForBiz(String token, BizIn bizIn) {
        // todo : front 토큰과 uuid로 받아올 것 변경예정
        Long userId = getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        String commaSeparatedString = transformToCommaSeparatedString(bizIn);
        Optional<ClubList> clubList = clubListRepository.findFirstByUserAndClub_ClubType(user,ClubType.BIZ);
        if (clubList.isEmpty()) {
            Long clubId = createClub(ClubType.BIZ,commaSeparatedString);
            createClubList(userId, clubId);
        } else {
            modifyClub(clubList.get().getClub(), commaSeparatedString);
        }
    }



    @Override
    public void createClubList(Long userId, Long clubId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("Club not found with ID: " + clubId));
        ClubList clubList = ClubList.builder()
                .user(user)
                .club(club)
                .build();
        log.info("클럽 만들었다 : {} {}",clubList.getUser(), clubList.getClub());
        clubListRepository.save(clubList);
        log.info("저장했다");
    }

    @Override
    @Transactional
    public void clear(String token, ClubType clubType) {
        Long userId = userService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        List<ClubList> clubLists = clubListRepository.findByUserAndClub_ClubType(user, clubType);

        if (!clubLists.isEmpty()) {
            List<Long> clubIds = clubLists.stream()
                    .map(clubList -> clubList.getClub().getId())
                    .toList();
            clubListRepository.deleteAll(clubLists); // 일치하는 모든 ClubList 항목을 삭제합니다
            clubRepository.deleteAllByIdIn(clubIds); // 일치하는 모든 club 항목을 삭제합니다.
        }
    }

    @Override
    public MomKidsOut getMomKidsData(String token) {
        Long userId = getUserIdFromToken(token);
        QClub club = QClub.club;
        QClubList clubList = QClubList.clubList;
        String result = query
                .select(club.clubContent)
                .from(club)
                .join(clubList)
                .on(clubList.club.eq(club))
                .where(clubList.user.id.eq(userId),
                        club.clubType.eq(ClubType.MOMKIDS))
                .fetchOne();
        if(result == null) {
            return MomKidsOut.builder().build();
        }
        String[] values = result.split(",", -1);
        MomKidsOut.MomKidsOutBuilder builder = MomKidsOut.builder();
        builder.sexFirst(values[0].isEmpty() ? null : values[0])
                .sexSecond(values[2].isEmpty() ? null : values[2]);
        try {
            builder.birthFirst(values[1].isEmpty() ? null : LocalDate.parse(values[1]));
            builder.birthSecond(values[3].isEmpty() ? null : LocalDate.parse(values[3]));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for birthFirst.", e);
        }
        return builder.build();
    }

    @Override
    public BizOut getBizData(String token) {
        Long userId = getUserIdFromToken(token);
        QClub club = QClub.club;
        QClubList clubList = QClubList.clubList;
        String result = query
                .select(club.clubContent)
                .from(club)
                .join(clubList)
                .on(clubList.club.eq(club))
                .where(clubList.user.id.eq(userId),
                        club.clubType.eq(ClubType.BIZ))
                .fetchOne();
        if(result == null) {
            return BizOut.builder().build();
        }
        String[] values = result.split(",", -1);

        return BizOut.builder()
                .bizCompany(values[0].isEmpty() ? null : values[0])
                .bizRegNumber(values[1].isEmpty() ? null : Integer.valueOf(values[1]))
                .bizRepresentative(values[2].isEmpty() ? null : values[2])
                .bizAddress(values[3].isEmpty() ? null : values[3])
                .bizEmail(values[4].isEmpty() ? null : values[4])
                .personalInfo(values[5].isEmpty() ? null : Boolean.valueOf(values[5]))
                .build();
    }

    @Override
    public CarOut getCarData(String token) {
        Long userId = getUserIdFromToken(token);
        QClub club = QClub.club;
        QClubList clubList = QClubList.clubList;
        String result = query
                .select(club.clubContent)
                .from(club)
                .join(clubList)
                .on(clubList.club.eq(club))
                .where(clubList.user.id.eq(userId),
                        club.clubType.eq(ClubType.CAR))
                .fetchOne();
        if(result == null) {
            return CarOut.builder().build();
        }
        String[] values = result.split(",", -1);
        return CarOut.builder()
                .regionNumber(values[0].isEmpty() ? null : values[0])
                .carNumber(values[1].isEmpty() ? null : Integer.valueOf(values[1]))
                .build();
    }

    @Override
    public AllStateOut getClubState(String token) {
        Long userId = getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        // todo : clubType마다 userId 당 한개씩 가져오는 로직
        return null;
    }

    private Long createClub(ClubType clubType,String content) {
        Club club = Club.builder()
                .clubType(clubType)
                .clubContent(content)
                .build();
        Club savedClub = clubRepository.save(club);
        return savedClub.getId();
    }
    private void modifyClub(Club club, String content) {
        club.setClubContent(content);
        clubRepository.save(club);
    }
    private Long getUserIdFromToken(String token) {return userService.getUserIdFromToken(token);}
    private String transformToCommaSeparatedString(Object obj) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
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
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // remove the last comma
        }
        return sb.toString();
    }

}
