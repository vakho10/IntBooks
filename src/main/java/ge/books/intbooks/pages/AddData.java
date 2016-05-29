package ge.books.intbooks.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.hibernate.Session;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.entities.Genre;
import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.BookService;

public class AddData {

	@Inject
	private Session session;

	@InjectService("BookService")
	private BookService bookService;

	@CommitAfter
	Object onActivate() {

		User admin = new User(1, true, "admin", "admin", new Date(), null, null);
		// Users
		{
			List<User> users = new ArrayList<User>();
			users.add(admin); // Admin
			users.add(new User(2, false, "vakho", "vakho", new Date(), null, null));
			users.add(new User(3, false, "gio", "gio", new Date(), null, null));
			users.add(new User(4, false, "asdf", "asdf", new Date(), null, null));
			for (User user : users) {
				session.save(user);
			}
		}

		Genre biography = new Genre(1, "ბიოგრაფია", null);
		Genre detective = new Genre(2, "დეტექტივი", null);
		Genre documentaryProse = new Genre(3, "დოკუმენტური პროზა", null);
		Genre historical = new Genre(4, "ისტორიული", null);
		Genre classical = new Genre(5, "კლასიკა", null);
		Genre story = new Genre(6, "მოთხრობა", null);
		Genre poetry = new Genre(7, "პოეზია", null);
		Genre prose = new Genre(8, "პროზა", null);
		Genre romance = new Genre(10, "რომანი", null);
		Genre child = new Genre(11, "საბავშვო", null);
		Genre teenager = new Genre(12, "მოზარდებისათვის", null);
		Genre adventure = new Genre(13, "სათავგადასავლო", null);
		Genre fantasy = new Genre(14, "ფანტასტიკა", null);
		Genre horrorMystic = new Genre(15, "ჰორორი/მისტიკა", null);

		// Genres
		{
			List<Genre> genres = new ArrayList<Genre>();

			genres.add(biography);
			genres.add(detective);
			genres.add(documentaryProse);
			genres.add(historical);
			genres.add(classical);
			genres.add(story);
			genres.add(poetry);
			genres.add(prose);
			genres.add(romance);
			genres.add(child);
			genres.add(teenager);
			genres.add(adventure);
			genres.add(fantasy);
			genres.add(horrorMystic);
			for (Genre genre : genres) {
				session.save(genre);
			}
		}

		// Books
		{
			// Book 1
			Book theAdventuresOfTomSawyer = new Book(1, "ტომ სოიერის თავგადასავალი", "მარკ ტვენი", 1876,
					"\"ტომ სოიერის თავგადასავალი \" მარკ ტვენის ყველაზე ცნობილი საბავშვო წიგნია, თუმცა, თავად ავტორი თურმე დიდხანს ყოყმანობდა, ვერ განესაზღვრა, ვისთვის იყო მისი წიგნები - დიდებისთვის თუ ბავშვებისთვის. მწერალმა ბოლოს ასე გადაწყვიტა: უმთავრესად ბიჭებისა და გოგონებისთვის ვწერდი, მაგრამ იმედი მაქვს, უფროსებიც წაიკითხავენ, რადგან ჩემი განზრახვა იყო, მათთვისაც გამეხსენებინა, როგორები იყვნენ ერთ დროს თავად ისინი, რას ფიქრობდნენ ან ამბობდნენ, ან რა უცნაურ ოინებს იგონებდნენ ხოლმეო. ამრიგად, ეს წიგნი თანაბარ სიამოვნებას მიანიჭებს ბავშვებსაც და უფროსებსაც.",
					"საბავშვო", admin, null, null,
					"http://www.clevelandhs.org/ourpages/webdesign4/marananjonathan/literatureimages/ING0812504208.jpg");
			bookService.createBook(theAdventuresOfTomSawyer,
					Arrays.asList(romance, classical, adventure, prose, teenager));

			// Book 2
			Book lordEdgewareDies = new Book(2, "ლორდ ეჯვერის მკვლელობა", "აგათა კრისტი", 1933,
					"ლონდონელ დიდებულს, ლორდ ეჯვერს, მისსავე სახლში კლავენ. თითქოს მიზეზი იოლად მისახვედრია – მდიდარი და გავლენიანი ოჯახის მეთაური ტირანი ბრძანდებოდა, ახლობლები მისი შიშით ძრწოდნენ, მაგრამ საქმე ის არის, რომ მკვლელობას წინ უძღოდა ლორდის სახლში ერთი ლამაზი ქალის, თანაც, როგორც გაირკვა, გადაცმულის, სტუმრობა. სახელგანთქმული მაძებარი ერკიულ პუარო შემთხვევით ხდება იმ მოვლენათა მოწმე და მონაწილეც კი, რომლებიც წინ უძღოდა მკვლელობას. ახლა მან, მისმა ერთგულმა მეგობარმა ჰასთინგზმა და კაპიტანმა ჯეპმა უნდა დაადგინონ, ვინ და რატომ გაითამაშა ეს „მასკარადი“, და აქვს თუ არა მას კავშირი მკვლელობასთან.",
					null, admin, null, null,
					"http://ecx.images-amazon.com/images/I/51LIDonzZvL._SX330_BO1,204,203,200_.jpg");
			bookService.createBook(lordEdgewareDies, Arrays.asList(detective, classical, prose));

			// Book 3
			Book evilUnderTheSun = new Book(3, "ბოროტება მზისქვეშეთში", "აგათა კრისტი", 1982,
					"საკმარისია, წიგნის მაღაზიაში ან ჯიხურში თვალი მოვკრა აგათა კრისტის რომელიმე დეტექტივს, რომ ჩემი განწყობა მყისიერად იცვლება და გადავდივარ მწერლის სამყაროში. სურვილი მიჩნდება, ვიყიდო, სახლისკენ მივიჩქარი, თვალი რომ გადავავლო თუნდაც ბავშვობაში წაკითხულს. გარდა იმისა, რომ აგათა კრისტის ნაწარმოებები მაღალღირებულ ლიტერატურულ თვისებებს ატარებს (თარგმანს გააჩნია), მისი კითხვისას სწყდები თანამედროვე ცხოვრების ორომტრიალს, ხშირად თავს აიგივებ მის გმირებთან და შეიძლება ისე დაგათენდეს, რომ ვერც კი გაიგო. მწერლის ყოველი რომანი ულტრათანამედროვე დეტექტივებისგან იმითაც განსხვავდება, რომ არ გაშინებს და მისი კოლიზიები მყარად იბუდებს მკითხველში. – გიზო ჟორდანია",
					null, admin, null, null,
					"http://ecx.images-amazon.com/images/I/51I-pmOw6rL._SX373_BO1,204,203,200_.jpg");
			bookService.createBook(evilUnderTheSun, Arrays.asList(detective, classical, prose, romance));

			// Book 4
			Book theTimeMachine = new Book(4, "The Time Machine", "ჰერბერტ უელსი", 1995,
					"A time machine propels the book's protagonist to the age of a slowly dying Earth. The brave explorer faces a future, burdened with mankind's greatest hopes and darkest fears. Published in 1895, this masterpiece launched H.G. Wells’s successful career and earned him his reputation as the father of science fiction. Thanks to Wells’s expert storytelling and provocative insight, \"The Time Machine\" will continue to enthrall readers for generations to come.",
					null, admin, null, null,
					"http://api.ning.com/files/FY1yyFKMjRx37XQYJmZzv491gygyI4uUBexC8jMc7uHihxdl*7oDI4La1dHriSZh7smztE9gERet83rqOm4FXnxhUI*jHANM/TheTimeMachinebook.jpg");
			bookService.createBook(theTimeMachine, Arrays.asList(romance, prose, classical, fantasy));

			// Book 5
			Book meBebiaIlikoDaIlarioni = new Book(5, "მე, ბებია, ილიკო და ილარიონი", "ნოდარ დუმბაძე", 1960,
					"ნოდარ დუმბაძის პირველი და ყველაზე პოპულარული რომანი. ნაწარმოების მთავარი გმირი, მთხრობელი ზურიკელა ვაშალომიძე გურიის სოფელ ხიდისთავში ცხოვრობს ბებიასთან ერთად. ბებიას შვილიშვილის აღზრდაში მეზობლები, ილიკო და ილარიონი ეხმარებიან. ნაწარმოების მოქმედება დიდი სამამულო ომის დროს ვითარდება. რომანის მიხედვით გადაღებულია ამავე სახელწოდების ფილმი.",
					null, admin, null, null,
					"https://saba.com.ge/content/images/book/b/49e4b513b5b14234afd232a877ef06fd.png");
			bookService.createBook(meBebiaIlikoDaIlarioni, Arrays.asList(romance, prose));

			// Book 6
			Book maradisobisKanoni = new Book(6, "მარადისობის კანონი", "ნოდარ დუმბაძე", 1978,
					"მარადისობის კანონი ნოდარ დუმბაძის ბოლო რომანია. სიკეთისა და ბოროტების მუდმივი ბრძოლის თემა მწერლის არც ამ ნაწარმოებს ტოვებს. რომანის მთავარი გმირის, საავადმყოფოში მძიმე მდგომარეობაში მყოფი ბაჩანას აზრით, მხოლოდ ხელოვნური გულის მქონე ადამიანს შეუძლია ბოროტებას მშიდად უცქიროს. ის საკუთარ ექიმს ეუბნება: \"სანამ ცოცხლები ვართ, ერთმანეთს ხელი უნდა გავუწოდოთ, რათა დავეხმაროთ ჩვენს სულებს უკვდავების მოპოვებაში ...\".",
					null, admin, null, null,
					"http://2.bp.blogspot.com/-Mgt6DggtY-I/TisN_qRdkRI/AAAAAAAAAAA/WtRcL4nJolc/s1600/maradisobis%2Bkanoni.jpg");
			bookService.createBook(maradisobisKanoni, Arrays.asList(romance, prose));

			// Book 7
			Book _112263 = new Book(7, "11/22/63", "სტივენ კინგი", 2011,
					"11/22/63 is a novel by Stephen King about a time traveler who attempts to prevent the assassination of President John F. Kennedy, which occurred on November 22, 1963. The novel was announced on King's official site on March 2, 2011.",
					null, admin, null, null, "https://upload.wikimedia.org/wikipedia/en/1/14/11-22-63.jpg");
			bookService.createBook(_112263, Arrays.asList(romance, prose, adventure, fantasy));

			// Book 8
			Book perilAtEndHouse = new Book(8, "საფრთხე  ენდჰაუსში", "აგათა კრისტი", 1932,
					"რომანში რამდენიმე სიუჟეტური ხაზი ერთდროულად ვითარდება და უცნაურად უკავშირდება ერთმანეთს - სასიყვარულო სამკუთხედი, ცნობილი მფრინავის დაღუპვა, ანდერძი, წერილები, მეტსახელი... ენდჰაუსი - ქალაქის განაპირას მდგარი ძველი სახლია. რა საფრთხე იმალება მის უწყინარ ფასადს მიღმა? ვინ არიან სინამდვილეში იქ მცხოვრები ადამიანები? ვინ არის მკვლელი? პუარო გამოძიებას იწყებს.! ცნობილი დეტექტივი გრანდიოზულ ფინალურ სპექტაკს გაითამაშებს, გარდაცვლილით მთავარ როლში...",
					null, admin, null, null,
					"http://www.socialbookshelves.com/wp-content/uploads/2013/10/Peril-at-end-House-grid__jpg_235x600_q95.jpg");
			bookService.createBook(perilAtEndHouse, Arrays.asList(romance, prose, detective));

			// Book 9
			Book deathOnTheNile = new Book(9, "სიკვდილი ნილოსზე", "აგათა კრისტი", 1937,
					"მრავალმილიონიანი ქონების მემკვიდრე ლიტენ რეჯუეი ცოლად მიჰყვება თავისი უახლოესი მეგობრის, ჟაკლინის საქმროს. ახალდაქორწინებულები თაფლობის თვის გასატარებლად თბომავალ „კარნაკით“ ნილოსზე, ეგვიპტეში მიემგზავრებიან. ჟაკლინი გადაწყვეტს, ჩააშხამოს მოგზაურობა მოღალატე საქმროსა და მეგობარს. ბორტზე სხვა მტრებიც არიან. სახელგანთქმული მეძებარი პუარო ლინეტს ერთ დღეს კაიუტაში მოკლულს იპოვის. ხანგრძლივი ძიების შემდეგ პუარო მიაგნებს დანაშაულის იარაღს და მიზეზსაც, მაგრამ ერთმანეთს ახალ-ახალი მკვლელობები მოჰყვება...",
					null, admin, null, null,
					"http://s3.amazonaws.com/agatha-christie-cms-production/hcuk-paperback/Death-on-the-Nile.JPG");
			bookService.createBook(deathOnTheNile, Arrays.asList(romance, prose, detective, classical));

			// Book 10
			Book appointmentWithDeath = new Book(10, "პაემანი სიკვდილთან", "აგათა კრისტი", 1938,
					"იერუსალიმის ღამის ლურჯი ცა. აივანი. რეიმონდ და ქეროლ ბოინტონები საკუთარი დედის მოკვდინებაზე საუბრობენ. ერკიულ პუარო დიალოგის უნებური მსმენელია. ვინ არის მისის ბოინტონი? რატომ აქვს თვალებში სიძულვილის ბნელი და გამანადგურებელი ნაკადი? რატომ სძულთ შვილებს? რატომ სძულს ყველას? მსხვერპლია თუ დამნაშავე? ვინ კლავს მოხუც ქალს? რას მალავს? ვის იცნობს სასტუმროს ფოიეში? მოქმედი გმირები: ნადინი, სარა კინგი, ექიმი ჟერარი, ლენოქსი, მისტერ ქოუფი, ჯინი, მის პირსი და მაჰმუდი. და ერთი იდუმალი ლედი. და მოქმედების ადგილი – პეტრა – მკვდარი ქალაქი. უდაბნოს „სიკვდილის ველი“. ცივი გონებითა და გასაოცარი სიზუსტით გათვლილ მკვლელობას ნიჭიერი დრამატურგი ჰყავს. მოტივი მრავალი. კიდევ, წამლის გატეხილი ბოთლი, ორი შპრიცი, და წარსულის ცოდვები. პუაროს ირგვლივ ეჭვმიტანილთა წრე ისევ იკვრება. მაძებარს საქმის გასახსნელად მხოლოდ 48 საათი აქვს.",
					null, admin, null, null, "http://d.gr-assets.com/books/1308808730l/16363.jpg");
			bookService.createBook(appointmentWithDeath, Arrays.asList(romance, prose, detective, classical));

			// Book 11
			Book afterTheFuneral = new Book(11, "დაკრძალვის შემდეგ", "აგათა კრისტი", 1953,
					"რიჩარდ ებერნეთის დაკრძალვის შემდგომ ნათესავები ანდერძის გასაცნობად იკრიბებიან. მისი სიკვდილი უეცარი, თუმცა კი მოსალოდნელი იყო. გარდაცვალების მიზეზად ბუნებრივ სიკვდილს მიიჩნევენ, მაგრამ გარდაცვლილის დის, კორას, შეძახილის შემდეგ: „ის ხომ მოკლეს, არა?“ ოჯახის ადვოკატს ეჭვი ეპარება, რომ რიჩარდ ებერთენი მოკლულია და გადაწყვეტს, საქმე გამოიძიოს. მალე მძინარე კორას ნაჯახით კლავენ, მაგრამ არა გაძარცვის მიზნით. ადვოკატ ენტუისლს ისღა დარჩენია, ცნობილ მაძებარს, ერკიულ პუაროს სთხოვოს დახმარება.",
					null, admin, null, null,
					"http://s3.amazonaws.com/agatha-christie-cms-production/hcuk-paperback/After-the-Funeral.JPG");
			bookService.createBook(afterTheFuneral, Arrays.asList(romance, prose, detective, classical));

			// Book 12
			Book thirdGirl = new Book(12, "მესამე გოგონა", "აგათა კრისტი", 1966,
					"ცნობილ მეძებარს, ერკიელ პუაროს, ერთ დილას გოგონა, სახელად ნორმა რესტარიკი, ესტუმრება რჩევის სათხოვნელად. გოგონას ჰგონია, რომ ვიღაც მოკლა, მაგრამ ვინ, არ იცის – მისი არც სახელი ახსოვს, არც მკვლელობის გარემოებები და არც ადგილი... ამჯერად პუარო მკვლელს კი არა, მკვლელობას ეძებს... და, რა თქმა უნდა, იპოვის.",
					null, admin, null, null, "http://d.gr-assets.com/books/1295681324l/16332.jpg");
			bookService.createBook(thirdGirl, Arrays.asList(romance, prose, detective, classical));

			// Book 13
			Book theGreatGatsby = new Book(13, "The Great Gatsby", "ფრენსის სკოტ ფიცჯერალდი", 1925,
					"The story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan is an exquisitely crafted tale of America in the 1920s.One of the great classics of twentieth-century literature, The Great Gatsby stands as the supreme achievement of F. Scott Fitzgerald’s career. This exemplary novel of the Jazz Age has been acclaimed by generations of readers.",
					null, admin, null, null, "https://upload.wikimedia.org/wikipedia/en/b/b0/Gatsby_1925_jacket.gif");
			bookService.createBook(theGreatGatsby, Arrays.asList(romance, prose, classical));

			// Book 14
			Book frankenstein = new Book(14, "Frankenstein", "მერი შელი", 1818,
					"Frankenstein tells the story of committed science student Victor Frankenstein. Obsessed with the idea of bestowing animation upon lifeless matter, he creates a human being from stolen body parts. Written by English novelist Marry Shelley, the classic tale of Frankenstein remains an important ancestor of both the horror and science fiction genres. The novel described as a Gothic thriller, a passionate romance, and a cautionary tale, not only tells a terrifying story but also raises profound, disturbing questions about the very nature of life.",
					null, admin, null, null, "http://i.huffpost.com/gen/975368/images/o-FRANKENSTEIN-facebook.jpg");
			bookService.createBook(frankenstein, Arrays.asList(romance, prose, classical));

			// Book 15
			Book treasureIsland = new Book(15, "Treasure Island", "რობერტ ლუის სტივენსონი", 1883,
					"The most popular pirate story ever written in English. The roaring adventure story depicts a deeply moving study of a boy’s growth into manhood, as he learns hard lessons about friendship, loyalty, courage and honor and the uncertain meaning of good and evil. First published in 1883, the novel has worked its way into the collective imagination of more than five generations of readers, young and old alike, gaining the power of myth.",
					"საბავშვო", admin, null, null,
					"http://www.iwasangelized1st.com/wp-content/uploads/2015/03/treasure-island1.gif");
			bookService.createBook(treasureIsland, Arrays.asList(romance, prose, classical));

			// Book 16
			Book harryPotterAndTheOrderOfThePhoenix = new Book(16, "Harry Potter and the Order of the Phoenix",
					"J. K. Rowling", 2003,
					"Harry Potter is due to start his fifth year at Hogwarts School of Witchcraft and Wizardry. His best friends Ron and Hermione have been very secretive all summer and he is desperate to get back to school and find out what has been going on. However, what Harry discovers is far more devastating than he could ever have expected...",
					"საბავშვო", admin, null, null, "https://domnardireviews.files.wordpress.com/2015/02/hp5cover.jpg");
			bookService.createBook(harryPotterAndTheOrderOfThePhoenix, Arrays.asList(fantasy, story, teenager));

			// Book 17
			Book harryPotterAndTheChamberOfSecrets = new Book(17, "Harry Potter and the Chamber of Secrets",
					"J. K. Rowling", 1998,
					"All Harry Potter wants is to get away from the Dursleys and go back to Hogwarts School for Witchcraft and Wizardry. But just as he's packing his bags, Harry receives a warning from a strange, impish creature named Dobby - who says that if Harry Potter returns to Hogwarts, disaster will strike.",
					"საბავშვო", admin, null, null,
					"https://upload.wikimedia.org/wikipedia/en/a/a7/Harry_Potter_and_the_Chamber_of_Secrets_(US_cover).jpg");
			bookService.createBook(harryPotterAndTheChamberOfSecrets, Arrays.asList(fantasy, story, teenager));

			// Book 18
			Book mystifyTheMagician = new Book(18, "Mystify the Magician", "Kathenire Applegate", 2001,
					"There is a place that shouldn’t exist. But does. And there are creatures that shouldn’t exist. But do. Welcome to a land where all of your dreams and nightmares are very real—and often deadly. Welcome to Everworld.",
					null, admin, null, null, "http://d.gr-assets.com/books/1375603595l/279667.jpg");
			bookService.createBook(mystifyTheMagician, Arrays.asList(fantasy, story, horrorMystic));

			// Book 19
			Book harryPotterAndTheHalfBloodPrince = new Book(19, "Harry Potter and the Half-Blood Prince",
					"J. K. Rowling", 2005,
					"It is the middle of the summer, but there is an unseasonal mist pressing against the windowpanes. Harry Potter is waiting nervously in his bedroom at the Dursleys' house in Privet Drive for a visit from Professor Dumbledore himself. One of the last times he saw the Headmaster was in a fierce one-to-one duel with Lord Voldemort, and Harry can't quite believe that Professor...",
					"საბავშვო", admin, null, null,
					"http://vignette3.wikia.nocookie.net/harrypotter/images/c/c6/Harry_potter_HBP_Scholastic_edition.jpg");
			bookService.createBook(harryPotterAndTheHalfBloodPrince, Arrays.asList(fantasy, story, teenager));

			// Book 20
			Book memoriesOfGeisha = new Book(20, "Memoirs of a Geisha", "Arthur Golden", 2005,
					"A literary sensation and runaway bestseller, this brilliant debut novel presents with seamless authenticity and exquisite lyricism the true confessions of one of Japan's most celebrated geisha.",
					null, admin, null, null, "http://d.gr-assets.com/books/1388367666l/930.jpg");
			bookService.createBook(memoriesOfGeisha, Arrays.asList(fantasy, story, horrorMystic));

			// Book 21
			Book timeTravelersWife = new Book(21, "The Time Traveler's Wife", "Audrey Niffenegger", 2003,
					"Audrey Niffenegger's dazzling debut is the story of Clare, a beautiful, strong-minded art student, and Henry, an adventuresome librarian, who have known each other since Clare was six and Henry was thirty-six, and were married when Clare was twenty-three and Henry thirty-one...",
					null, admin, null, null, "http://d.gr-assets.com/books/1380660571l/18619684.jpg");
			bookService.createBook(timeTravelersWife, Arrays.asList(fantasy, story, horrorMystic));

			// Book 22
			Book harryPotterAndTheGobletOfFire = new Book(22, "Harry Potter and the Goblet of Fire", "J. K. Rowling",
					2000,
					"The summer holidays are dragging on and Harry Potter can't wait for the start of the school year. It is his fourth year at Hogwarts School of Witchcraft and Wizardry and there are spells to be learnt and (unluckily) Potions and Divination lessons to be attended. But Harry can't know that the atmosphere is darkening around him, and his worst enemy is preparing a fate...",
					"საბავშვო", admin, null, null,
					"https://upload.wikimedia.org/wikipedia/en/6/62/Harry_Potter_and_the_Goblet_of_Fire_(US_cover).jpg");
			bookService.createBook(harryPotterAndTheGobletOfFire, Arrays.asList(fantasy, story, teenager));

			// Book 23
			Book lifeOfPi = new Book(23, "Life of Pi", "Yann Martel", 2001,
					"Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor \"Pi\" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker.",
					"საბავშვო", admin, null, null,
					"https://upload.wikimedia.org/wikipedia/en/4/45/Life_of_Pi_cover.png");
			bookService.createBook(lifeOfPi, Arrays.asList(story, teenager));

			// Book 24
			Book theGirlWithDragonTattoo = new Book(24, "The Girl with the Dragon Tattoo", "Stieg Larsson", 2005,
					"Mikael Blomkvist, a once-respected financial journalist, watches his professional life rapidly crumble around him. Prospects appear bleak until an unexpected (and unsettling) offer to resurrect his name is extended by an old-school titan of Swedish industry. The catch—and there's always a catch—is that Blomkvist must first spend a year researching a mysterious disappearanc...",
					null, admin, null, null,
					"http://vignette4.wikia.nocookie.net/milleniumtrilogy/images/9/9f/Dragon-tattoo-cover.png");
			bookService.createBook(theGirlWithDragonTattoo, Arrays.asList(story, teenager, horrorMystic));

			// Book 25
			Book sarahsKey = new Book(25, "Sarah's Key", "Tatiana de Rosnay", 2007,
					"Paris, July 1942: Sarah, a ten year-old girl, is brutally arrested with her family by the French police in the Vel' d'Hiv' roundup, but not before she locks her younger brother in a cupboard in the family's apartment, thinking that she will be back within a few hours.",
					null, admin, null, null, "http://d.gr-assets.com/books/1438863728l/556602.jpg");
			bookService.createBook(sarahsKey, Arrays.asList(story, teenager));

			// Book 26
			Book johnAdams = new Book(26, "John Adams", "David McCullough", 2001,
					"In this powerful, epic biography, David McCullough unfolds the adventurous life-journey of John Adams, the brilliant, fiercely independent, often irascible, always honest Yankee patriot -- \"the colossus of independence,\" as Thomas Jefferson called him -- who spared nothing in his zeal for the American Revolution; who rose to become the second President of the United States...",
					null, admin, null, null, "https://upload.wikimedia.org/wikipedia/en/9/9d/John_Adams_book.jpg");
			bookService.createBook(johnAdams, Arrays.asList(story, prose, documentaryProse));

			// Book 27
			Book whatIsTheWhat = new Book(27, "What is the What", "Dave Eggers", 2006,
					"In aheartrending and astonishingnovel, Eggers illuminates the history of the civil war in Sudan through the eyes of Valentino Achak Deng, a refugee now living in the United States. We follow his life as he's driven from his home as a boy and walks, with thousands of orphans, to Ethiopia, where he finds safety — for a time...",
					null, admin, null, null, "http://farm7.static.flickr.com/6108/6222720077_94d9b65b53.jpg");
			bookService.createBook(whatIsTheWhat, Arrays.asList(prose, documentaryProse));

			// Book 28
			Book houseOfLeaves = new Book(28, "House of Leaves", "Mark Z. Danielewski", 2000,
					"Years ago, when House of Leaves was first being passed around, it was nothing more than a badly bundled heap of paper, parts of which would occasionally surface on the Internet. No one could have anticipated the small but devoted following this terrifying story would soon command.",
					null, admin, null, null, "https://upload.wikimedia.org/wikipedia/en/d/de/House_of_leaves.jpg");
			bookService.createBook(houseOfLeaves, Arrays.asList(prose, documentaryProse));

			// Book 29
			Book theShack = new Book(29, "The Shack", "Wm. Paul Young", 2007,
					"Mackenzie Allen Philips' youngest daughter, Missy, has been abducted during a family vacation, and evidence that she may have been brutally murdered is found in an abandoned shack deep in the Oregon wilderness. Four years later in the midst of his \"Great Sadness,\" Mack receives a suspicious note, apparently from God, inviting him back to that shack for a weekend.",
					null, admin, null, null, "http://theshackbook.com/aimages/shackover.jpg");
			bookService.createBook(theShack, Arrays.asList(prose, fantasy));

			// Book 30
			Book noCountryForOldMen = new Book(30, "No Country For Old Men", "Cormac McCarthy", 2006,
					"In his blistering new novel, Cormac McCarthy returns to the Texas-Mexico border, setting of his famed Border Trilogy. The time is our own, when rustlers have given way to drug-runners and small towns have become free-fire zones.",
					null, admin, null, null, "http://bookcoverarchive.com/wp-content/uploads/amazon/no_country_for_old_men.jpg");
			bookService.createBook(noCountryForOldMen, Arrays.asList(prose, fantasy));
		}

		// Reviews
		{
			bookService.writeBookReview(1, 1, 7, "I lika do da chacha!");
			bookService.writeBookReview(1, 1, 4, "I lika do da chacha2222!");
			bookService.writeBookReview(2, 1, 10, "The best book about crime.");
			bookService.writeBookReview(2, 2, 2,
					"I liked the part where the doctor killed the duke... (<-spoilers) :d");
			bookService.writeBookReview(2, 3, 7, null);
			bookService.writeBookReview(1, 1, 3, null);
			bookService.writeBookReview(1, 4, 1, null);
		}
		return Index.class;
	}

}
