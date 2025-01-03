package com.mhsk.wordssak.common.config;

import com.mhsk.wordssak.word.entity.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultWordConfig {
  public List<Word> getDefaultWords(Integer grade, Integer semester, Integer unit) {
    List<Word> defaultWords = new ArrayList<>();

    if (grade == 3) {
      switch (unit) {
        case 1 -> {
          defaultWords.add(new Word("hi[hello]", "안녕, 안녕하세요", "Hi, I'm Uju."));
          defaultWords.add(new Word("bye", "안녕히 가세요", "Bye, see you tomorrow."));
          defaultWords.add(new Word("sorry", "미안해요", "I'm sorry for being late."));
          defaultWords.add(new Word("thank", "감사합니다", "Thank you for your help."));
          defaultWords.add(new Word("welcome", "천만에요", "You're welcome."));
          defaultWords.add(new Word("that", "그것", "That's okay, no problem."));
          defaultWords.add(new Word("you", "너(당신), 여러분", "You are kind."));
        }
        case 2 -> {
          defaultWords.add(new Word("ball", "공", "It's a ball."));
          defaultWords.add(new Word("dog", "개", "Look, it's a dog."));
          defaultWords.add(new Word("this", "이것", "What's this? It's a fan."));
          defaultWords.add(new Word("what", "무엇", "What is it? It's a ball."));
        }
        case 3 -> {
          defaultWords.add(new Word("sit", "앉다", "Sit down, please."));
          defaultWords.add(new Word("stand", "서다", "Stand up, everyone."));
          defaultWords.add(new Word("down", "아래로, 내려가다", "The ball rolled down."));
          defaultWords.add(new Word("please", "제발, 부탁합니다", "Please close the door."));
        }
        case 4 -> {
          defaultWords.add(new Word("apple", "사과", "How many apples? Two apples."));
          defaultWords.add(new Word("many", "많은", "How many books do you have?"));
          defaultWords.add(new Word("how", "어떻게, 얼마나", "How are you today?"));
        }
        case 5 -> {
          defaultWords.add(new Word("pencil", "연필", "Do you have a pencil? Yes, I do."));
          defaultWords.add(new Word("book", "책", "I have a book."));
          defaultWords.add(new Word("eraser", "지우개", "Where is my eraser?"));
          defaultWords.add(new Word("have", "가지다", "I have a ruler."));
        }
        case 6 -> {
          defaultWords.add(new Word("color", "색깔", "What color is it? It's blue."));
          defaultWords.add(new Word("blue", "파란색", "The sky is blue."));
          defaultWords.add(new Word("red", "빨간색", "My bag is red."));
        }
        case 7 -> {
          defaultWords.add(new Word("like", "좋아하다", "Do you like milk? Yes, I do."));
          defaultWords.add(new Word("milk", "우유", "I don't like milk."));
          defaultWords.add(new Word("bread", "빵", "I like bread and butter."));
        }
        case 8 -> {
          defaultWords.add(new Word("big", "큰", "It's big and heavy."));
          defaultWords.add(new Word("small", "작은", "The giraffe is big, but the bird is small."));
          defaultWords.add(new Word("tall", "키가 큰", "The giraffe is very tall."));
        }
        case 9 -> {
          defaultWords.add(new Word("can", "할 수 있다", "Can you jump? Yes, I can."));
          defaultWords.add(new Word("swim", "수영하다", "I can swim in the pool."));
          defaultWords.add(new Word("walk", "걷다", "Let's walk to school."));
        }
        case 10 -> {
          defaultWords.add(new Word("mom", "엄마", "Who's she? She's my mom."));
          defaultWords.add(new Word("dad", "아빠", "My dad is strong."));
          defaultWords.add(new Word("pretty", "예쁜", "She's very pretty."));
        }
        case 11 -> {
          defaultWords.add(new Word("cold", "추운", "It's cold outside."));
          defaultWords.add(new Word("sunny", "화창한", "How's the weather? It's sunny."));
          defaultWords.add(new Word("play", "놀다", "Let's play outside."));
        }
        default -> defaultWords.add(new Word("default", "기본 단어", "This is a default word."));
      }
    }

    if (grade == 4) {
      switch (unit) {
        case 1 -> {
          defaultWords.add(new Word("father", "아버지", "This is my father."));
          defaultWords.add(new Word("friend", "친구", "Eric is my best friend."));
          defaultWords.add(new Word("meet", "만나다", "Nice to meet you."));
          defaultWords.add(new Word("mother", "어머니", "This is my mother."));
          defaultWords.add(new Word("name", "이름", "My name is Eric."));
          defaultWords.add(new Word("teacher", "선생님", "She is my English teacher."));
        }
        case 2 -> {
          defaultWords.add(new Word("bad", "나쁜", "That's a bad idea."));
          defaultWords.add(new Word("soccer", "축구", "Let's play soccer together."));
          defaultWords.add(new Word("sound", "소리, 들리다", "Sounds good to me."));
          defaultWords.add(new Word("together", "함께", "We can play together."));
        }
        case 3 -> {
          defaultWords.add(new Word("angry", "화난", "Are you angry?"));
          defaultWords.add(new Word("happy", "행복한", "I'm happy today."));
          defaultWords.add(new Word("sad", "슬픈", "Why are you sad?"));
          defaultWords.add(new Word("tired", "피곤한", "I'm so tired after school."));
          defaultWords.add(new Word("water", "물", "Can I have some water?"));
        }
        case 4 -> {
          defaultWords.add(new Word("bottle", "병", "Put the bottle here."));
          defaultWords.add(new Word("push", "밀다", "Don't push the door too hard."));
          defaultWords.add(new Word("put", "놓다", "Put your bag on the desk."));
          defaultWords.add(new Word("stop", "멈추다", "Stop running in the hallway!"));
        }
        case 5 -> {
          defaultWords.add(new Word("cap", "모자", "Where is my cap?"));
          defaultWords.add(new Word("desk", "책상", "It's on the desk."));
          defaultWords.add(new Word("under", "~ 아래에", "The ball is under the table."));
          defaultWords.add(new Word("where", "어디", "Where is your book?"));
        }
        case 6 -> {
          defaultWords.add(new Word("breakfast", "아침 식사", "I eat breakfast at 8 a.m."));
          defaultWords.add(new Word("time", "시간", "What time is it now?"));
          defaultWords.add(new Word("late", "늦은", "Don't be late for school."));
          defaultWords.add(new Word("school", "학교", "It's time for school."));
        }
        case 7 -> {
          defaultWords.add(new Word("watch", "시계", "Is this your watch?"));
          defaultWords.add(new Word("umbrella", "우산", "My umbrella is blue."));
        }
        case 8 -> {
          defaultWords.add(new Word("pilot", "조종사", "What do you do? I'm a pilot."));
          defaultWords.add(new Word("scientist", "과학자", "He is a great scientist."));
          defaultWords.add(new Word("cook", "요리사", "My uncle is a cook."));
        }
        case 9 -> {
          defaultWords.add(new Word("busy", "바쁜", "I'm busy with my homework."));
          defaultWords.add(new Word("sleep", "자다", "I sleep early on weekdays."));
          defaultWords.add(new Word("wash", "씻다, 세탁하다", "Wash the dishes after dinner."));
        }
        case 10 -> {
          defaultWords.add(new Word("much", "많은, 얼마나", "How much is it?"));
          defaultWords.add(new Word("toy", "장난감", "The toy is 500 won."));
          defaultWords.add(new Word("will", "~할 것이다", "I will buy the car."));
        }
        case 11 -> {
          defaultWords.add(new Word("early", "일찍", "I get up early every day."));
          defaultWords.add(new Word("every", "매, 모든", "I exercise every morning."));
          defaultWords.add(new Word("diary", "일기", "I keep a diary every day."));
          defaultWords.add(new Word("ride", "타다", "I ride my bike to school."));
        }
        default -> {
          defaultWords.add(new Word("default", "기본 단어", "This is a default word."));
        }
      }
    }

    if (grade == 5) {
      switch (unit) {
        case 1 -> {
          defaultWords.add(new Word("afternoon", "오후", "Good afternoon! How are you?"));
          defaultWords.add(new Word("from", "~에서, ~출신인", "Where are you from? I'm from Korea."));
          defaultWords.add(new Word("morning", "아침", "Good morning! Nice to meet you."));
          defaultWords.add(new Word("evening", "저녁", "Good evening! How was your day?"));
          defaultWords.add(new Word("traditional", "전통적인", "This is a traditional Korean house."));
        }
        case 2 -> {
          defaultWords.add(new Word("usually", "보통", "I usually go to the park on Sundays."));
          defaultWords.add(new Word("weekend", "주말", "What do you do on weekends?"));
          defaultWords.add(new Word("practice", "연습", "I have soccer practice on Saturdays."));
          defaultWords.add(new Word("feed", "먹이를 주다", "I feed the cows on the farm."));
        }
        case 3 -> {
          defaultWords.add(new Word("may", "~해도 되다", "May I use your phone?"));
          defaultWords.add(new Word("borrow", "빌리다", "Can I borrow a pen from you?"));
          defaultWords.add(new Word("restroom", "화장실", "Where is the restroom?"));
          defaultWords.add(new Word("wheelchair", "휠체어", "The museum provides wheelchairs for visitors."));
        }
        case 4 -> {
          defaultWords.add(new Word("sock", "양말", "Whose sock is this?"));
          defaultWords.add(new Word("button", "단추", "The button on my coat is missing."));
          defaultWords.add(new Word("long", "긴", "She has long hair."));
          defaultWords.add(new Word("ready", "준비된", "Are you ready to go?"));
        }
        case 5 -> {
          defaultWords.add(new Word("order", "주문", "May I take your order?"));
          defaultWords.add(new Word("fry", "튀기다", "I’d like fried rice, please."));
          defaultWords.add(new Word("sweet", "달콤한", "This cake is sweet and delicious."));
          defaultWords.add(new Word("beef", "소고기", "Bulgogi is a Korean beef dish."));
        }
        case 6 -> {
          defaultWords.add(new Word("vacation", "휴가", "What will you do this summer vacation?"));
          defaultWords.add(new Word("join", "참가하다", "I’ll join a space camp."));
          defaultWords.add(new Word("space", "우주", "I want to learn about space exploration."));
          defaultWords.add(new Word("cousin", "사촌", "My cousin is visiting us next week."));
        }
        case 7 -> {
          defaultWords.add(new Word("during", "동안", "What did you do during the vacation?"));
          defaultWords.add(new Word("uncle", "삼촌", "I visited my uncle in Jeju-do."));
          defaultWords.add(new Word("picnic", "소풍", "We had a picnic by the sea."));
        }
        case 8 -> {
          defaultWords.add(new Word("buy", "사다", "I want to buy these sunglasses."));
          defaultWords.add(new Word("size", "크기", "What size do you want?"));
          defaultWords.add(new Word("jeans", "청바지", "How much are these jeans?"));
        }
        case 9 -> {
          defaultWords.add(new Word("favorite", "가장 좋아하는", "What’s your favorite subject?"));
          defaultWords.add(new Word("subject", "과목", "My favorite subject is art."));
          defaultWords.add(new Word("draw", "그리다", "I like to draw pictures in my free time."));
        }
        case 10 -> {
          defaultWords.add(new Word("living room", "거실", "There is a sofa in the living room."));
          defaultWords.add(new Word("sofa", "소파", "The sofa in the living room is very comfortable."));
          defaultWords.add(new Word("stove", "스토브, 난로", "The stove is in the kitchen."));
          defaultWords.add(new Word("bathroom", "욕실", "The bathroom is next to the bedroom."));
        }
        case 11 -> {
          defaultWords.add(new Word("director", "감독", "I want to be a movie director."));
          defaultWords.add(new Word("comedian", "코미디언", "I want to make people laugh as a comedian."));
          defaultWords.add(new Word("engineer", "엔지니어", "My brother is an engineer."));
          defaultWords.add(new Word("traveler", "여행자", "I want to be a traveler and explore the world."));
        }
        default -> {
          defaultWords.add(new Word("default", "기본 단어", "This is a default word."));
        }
      }
    }

    if (grade == 6) {
      switch (unit) {
        case 1 -> {
          defaultWords.add(new Word("grade", "학년", "What grade are you in?"));
          defaultWords.add(new Word("spell", "철자를 말하다", "How do you spell your name?"));
          defaultWords.add(new Word("famous", "유명한", "He is a famous guitarist."));
          defaultWords.add(new Word("guitarist", "기타리스트", "The guitarist played beautifully."));
          defaultWords.add(new Word("drummer", "드러머", "The drummer is amazing."));
        }
        case 2 -> {
          defaultWords.add(new Word("medicine", "약", "Take this medicine for your fever."));
          defaultWords.add(new Word("rest", "휴식", "Get some rest and feel better."));
          defaultWords.add(new Word("stomachache", "복통", "I have a stomachache."));
          defaultWords.add(new Word("toothache", "치통", "She went to the dentist for her toothache."));
          defaultWords.add(new Word("warm", "따뜻한", "The tea is warm and soothing."));
        }
        case 3 -> {
          defaultWords.add(new Word("festival", "축제", "When is the club festival?"));
          defaultWords.add(new Word("concert", "콘서트", "The concert is on Saturday."));
          defaultWords.add(new Word("trip", "여행", "We are planning a school trip."));
          defaultWords.add(new Word("ukulele", "우쿨렐레", "She plays the ukulele very well."));
        }
        case 4 -> {
          defaultWords.add(new Word("block", "블록, 구역", "Go straight two blocks and turn left."));
          defaultWords.add(new Word("turn", "돌다", "Turn right at the corner."));
          defaultWords.add(new Word("between", "~사이에", "The store is between the bank and the post office."));
          defaultWords.add(new Word("garden", "정원", "There is a beautiful garden behind the museum."));
        }
        case 5 -> {
          defaultWords.add(new Word("tomorrow", "내일", "What are you going to do tomorrow?"));
          defaultWords.add(new Word("finish", "끝내다", "I need to finish my homework tonight."));
          defaultWords.add(new Word("special", "특별한", "Today is a special day for me."));
        }
        case 6 -> {
          defaultWords.add(new Word("curly", "곱슬한", "She has short curly hair."));
          defaultWords.add(new Word("beautiful", "아름다운", "The flowers in the garden are beautiful."));
          defaultWords.add(new Word("short", "짧은", "His hair is short and neat."));
        }
        case 7 -> {
          defaultWords.add(new Word("often", "자주", "How often do you eat breakfast?"));
          defaultWords.add(new Word("healthy", "건강한", "Eating vegetables is healthy."));
          defaultWords.add(new Word("habit", "습관", "Brushing your teeth twice a day is a good habit."));
        }
        case 8 -> {
          defaultWords.add(new Word("strong", "강한", "He is stronger than his brother."));
          defaultWords.add(new Word("heavy", "무거운", "This box is heavy."));
          defaultWords.add(new Word("win", "이기다", "Who will win the arm-wrestling match?"));
        }
        case 9 -> {
          defaultWords.add(new Word("think", "생각하다", "What do you think about the movie?"));
          defaultWords.add(new Word("interesting", "재미있는", "The story was interesting and fun to read."));
          defaultWords.add(new Word("difficult", "어려운", "Math can be difficult sometimes."));
        }
        case 10 -> {
          defaultWords.add(new Word("invent", "발명하다", "Thomas Edison invented the light bulb."));
          defaultWords.add(new Word("clock", "시계", "The clock on the tower is huge."));
          defaultWords.add(new Word("tower", "탑", "The Eiffel Tower is in Paris."));
        }
        case 11 -> {
          defaultWords.add(new Word("save", "절약하다, 보호하다", "We should save energy to help the earth."));
          defaultWords.add(new Word("recycle", "재활용하다", "It's important to recycle paper and plastic."));
          defaultWords.add(new Word("stairs", "계단", "How about using the stairs instead of the elevator?"));
        }
        default -> {
          defaultWords.add(new Word("default", "기본 단어", "This is a default word."));
        }
      }
    }

    return defaultWords;
  }
}
