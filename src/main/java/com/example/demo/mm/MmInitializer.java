package com.example.demo.mm;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.utils.Utils;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class MmInitializer {
//
//	private static final int NUMBER_TO_GENERATE = 5_000;
////	private static final int NUMBER_TO_GENERATE = 10;
//	private MmRepository mmRepository;
//
//	@Autowired
//	private Utils util;
//
//	public MmInitializer(MmRepository mmRepository) {
//		this.mmRepository = mmRepository;
//	}
//
////    @PostConstruct
////    public void init() {
////        log.info("generating {} random employees", NUMBER_TO_GENERATE);
////        Faker faker = new Faker();
////        for (int i = 1; i <= NUMBER_TO_GENERATE; i++) {
////            Mm mm = Mm.builder()
////                    .id(i)
////                    .firstName(faker.name().firstName())
////                    .lastName(faker.name().lastName())
////                    .build();
////            mmRepository.save(mm);
////            System.out.println("Count: " + i);
////        }
////    }
//
////    @PostConstruct
////    public void init() {
////        log.info("generating {} random Logins", NUMBER_TO_GENERATE);
////        Faker faker = new Faker();
////        Random randomGenerator = new Random();
////        for (int i = 1; i <= NUMBER_TO_GENERATE; i++) {
////            Mm mm = Mm.builder()
////                    .id(i)
////                    .firstName(faker.name().firstName())
////                    .lastName(faker.name().lastName())
////                    .build();
////            mmRepository.save(mm);
////            System.out.println("Count: " + i);
////            int b_i = randomGenerator.nextInt(6);
////            util.registerLog(Long.valueOf(USERS[randomGenerator.nextInt(14)]), 
////            (randomGenerator.nextInt(198) + 101) + "." + 
////            		(randomGenerator.nextInt(198) + 101)+"." +
////            (randomGenerator.nextInt(99)) + "." + 
////            		(randomGenerator.nextInt(99)) , BROWSERS[b_i],
////            		BROWSER_VERSIONS[b_i], ACTIONS[randomGenerator.nextInt(2)], "1", "1");
////            System.out.println("Count: " + i);
////        }
////    }
////    
////    private static final String[] BROWSERS = new String[] {
////    		"Chrome",
////    		"Firefox",
////    		"Safari",
////    		"Edge",
////    		"Opera",
////    		"Internet Explorer"
////    };
////    
////    private static final String[] BROWSER_VERSIONS = new String[] {
////    		"Chrome 109",
////    		"Firefox 109",
////    		"Safari 16",
////    		"Edge 1370.52",
////    		"Opera 95",
////    		"Internet Explorer 11"
////    };
////    
////    private static final String[] ACTIONS = new String[] {
////    		"Login",
////    		"Logout",
////    		"Logout-all-sessions",
////    };
////    
////    private static final int[] USERS = new int[] {
////    		30127,
////    		30131,
////    		40131,
////    		40132,
////    		40134,
////    		40135,
////    		40136,
////    		40137,
////    		40138,
////    		40139,
////    		40140,
////    		40141,
////    		40142,
////    		40144
////    };
//
////	@PostConstruct
////	public void init() {
////		log.info("generating {} random Logins", NUMBER_TO_GENERATE);
////		Faker faker = new Faker();
////		Random randomGenerator = new Random();
////		for (int i = 1; i <= NUMBER_TO_GENERATE; i++) {
////			Mm mm = Mm.builder().id(i).firstName(faker.name().firstName()).lastName(faker.name().lastName()).build();
////			mmRepository.save(mm);
////			System.out.println("Count: " + i);
////			int b_i = randomGenerator.nextInt(6);
////			util.registerActivityMM(Long.valueOf(USERS[randomGenerator.nextInt(14)]), 
////					ACTIONS[randomGenerator.nextInt(9)], "-");
////			System.out.println("Count: " + i);
////		}
////	}
////
////	private static final String[] ACTIONS = new String[] { "Update Functionality", "View Functionalities", "View Logs",
////			"View Users", "View user activities", "Activate Role", "Deactivate Role", "View Roles", "Register User" };
////
////	private static final int[] USERS = new int[] { 30127, 30131, 40131, 40132, 40134, 40135, 40136, 40137, 40138, 40139,
////			40140, 40141, 40142, 40144 };
//
//}
