/*
 * @author Saurabh Nailwal
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class fileParser {

	// function for removing stop words and special characters
	public static String removeStopWordsAndSpecial(String text)
			throws IOException {

		String remStopWords = "";

		// Removing punctuations so words can be matched
		text = text.replaceAll("\\. |\\, |\\? |\\! |\\: |\\; | \\(|\\) ", " ");

		String[] items = text.split(" ");

		for (int i = 0; i < items.length; i++) {
			remStopWords += (" " + items[i].toLowerCase().trim() + " ")
					.replaceAll(
							" a | a's | able | about | above | according |"
									+ " accordingly | across | actually | after | afterwards | again | against | ain't | all |"
									+ " allow | allows | almost | alone | along | already | also | although | always | am | among |"
									+ " amongst | an | and | another | any | anybody | anyhow | anyone | anything | anyway |"
									+ " anyways | anywhere | apart | appear | appreciate | appropriate | are | aren't | around |"
									+ " as | aside | ask | asking | associated | at | available | away | awfully | b | be | became |"
									+ " because | become | becomes | becoming | been | before | beforehand | behind | being |"
									+ " believe | below | beside | besides | best | better | between | beyond | both | brief | but |"
									+ " by | c | c'mon | c's | came | can | can't | cannot | cant | cause | causes | certain | certainly |"
									+ " changes | clearly | co | com | come | comes | concerning | consequently | consider |"
									+ " considering | contain | containing | contains | corresponding | could | couldn't |"
									+ " course | currently | d | definitely | described | despite | did | didn't | different |"
									+ " do | does | doesn't | doing | don't | done | down | downwards | during | e | each | edu | eg |"
									+ " eight | either | else | elsewhere | enough | entirely | especially | et | etc | even |"
									+ " ever | every | everybody | everyone | everything | everywhere | ex | exactly | example |"
									+ " except | f | far | few | fifth | first | five | followed | following | follows | for | former |"
									+ " formerly | forth |four | from | further | furthermore | g | get | gets | getting | given |"
									+ " gives | go | goes | going | gone | got | gotten | greetings | h | had | hadn't | happens |"
									+ " hardly | has | hasn't | have | haven't | having | he | he's | hello | help | hence | her |"
									+ " here | here's | hereafter | hereby | herein | hereupon | hers | herself | hi | him |"
									+ " himself | his | hither | hopefully | how | howbeit | however | i | i'd | i'll | i'm | i've |"
									+ " ie | if | ignored | immediate | in | inasmuch | inc | indeed | indicate | indicated |"
									+ " indicates | inner | insofar | instead | into | inward | is | isn't | it | it'd | it'll |"
									+ " it's | its | itself | j | just | k | keep | keeps | kept | know | knows | known | l | last |"
									+ " lately | later | latter | latterly | least | less | lest | let | let's | like | liked |"
									+ " likely | little | look | looking | looks | ltd | m | mainly | many | may | maybe | me | mean |"
									+ " meanwhile | merely | might | more | moreover | most | mostly | much | must | my | myself |"
									+ " n | name | namely | nd | near | nearly | necessary | need | needs | neither | never |"
									+ " nevertheless | new | next | nine | no | nobody | non | none | noone | nor | normally | not |"
									+ " nothing | novel | now | nowhere | o | obviously | of | off | often | oh | ok | okay | old | on |"
									+ " once | one | ones | only | onto | or | other | others | otherwise |ought | our | ours |"
									+ " ourselves | out | outside | over | overall | own | p | particular | particularly | per |"
									+ " perhaps | placed | please | plus | possible | presumably | probably | provides | q |"
									+ " que | quite | qv | r | rather | rd | re | really | reasonably | regarding | regardless |"
									+ " regards | relatively | respectively | right | s | said | same | saw | say | saying | says |"
									+ " second | secondly | see | seeing | seem | seemed | seeming | seems | seen | self | selves |"
									+ " sensible | sent | serious | seriously | seven | several | shall | she | should | shouldn't |"
									+ " since | six | so | some | somebody | somehow | someone | something | sometime | sometimes |"
									+ " somewhat | somewhere | soon | sorry | specified | specify | specifying | still | sub | such |"
									+ " sup | sure | t | t's | take | taken | tell | tends | th | than | thank | thanks | thanx | that |"
									+ " that's | thats | the | their | theirs | them | themselves | then | thence | there | there's |"
									+ " thereafter | thereby | therefore | therein | theres | thereupon | these | they | they'd |"
									+ " they'll | they're | they've | think | third | this | thorough | thoroughly | those | though |"
									+ " three | through | throughout | thru | thus | to | together | too | took | toward | towards |"
									+ " tried | tries | truly | try | trying | twice | two | u | un | under | unfortunately | unless |"
									+ " unlikely | until | unto | up | upon | us | use | used | useful | uses | using | usually | uucp |"
									+ " v | value | various | very | via | viz | vs | w | want | wants | was | wasn't | way | we | we'd | we'll |"
									+ " we're | we've | welcome | well | went | were | weren't | what | what's | whatever | when |"
									+ " whence | whenever | where | where's | whereafter | whereas | whereby | wherein |"
									+ " whereupon | wherever | whether | which | while | whither | who | who's | whoever | whole |"
									+ " whom | whose | why | will | willing | wish | with | within | without | won't | wonder | would |"
									+ " would | wouldn't | x | y | yes | yet | you | you'd | you'll | you're | you've | your | yours |"
									+ " yourself | yourselves | z | zero ", "");
		}

		String formattedText = (remStopWords.trim() + " ").replaceAll(
				"[^a-zA-Z0-9]+", " ");

		return formattedText;

	}


	public static void main(String[] args) throws IOException {

		//Destination path for the output text files
		String filepath = "/home/saurabh/Downloads/yelpdata/ReviewText/";

		//Source path for reading the json file
		JsonReader jsonReader = new JsonReader(
				new FileReader(
						"/home/saurabh/Downloads/yelpdata/yelp_review_data.json"));
	

		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;

		StringBuilder star1 = new StringBuilder();
		StringBuilder star2 = new StringBuilder();
		StringBuilder star3 = new StringBuilder();
		StringBuilder star4 = new StringBuilder();
		StringBuilder star5 = new StringBuilder();

		String file1 = "ReviewText1", file2 = "ReviewText2", file3 = "ReviewText3", file4 = "ReviewText4", file5 = "ReviewText5";

		try {
			jsonReader.setLenient(true);

			while (jsonReader.peek().toString().equals("BEGIN_OBJECT")) {

				jsonReader.beginObject();
				// votes
				jsonReader.nextName();

				// funny
				jsonReader.beginObject();
				jsonReader.nextName();
				jsonReader.nextInt();

				// useful
				jsonReader.nextName();
				jsonReader.nextInt();

				// cool
				jsonReader.nextName();
				jsonReader.nextInt();
				jsonReader.endObject();

				// user id
				jsonReader.nextName();
				jsonReader.nextString();

				// review id
				jsonReader.nextName();
				jsonReader.nextString();

				// stars int
				jsonReader.nextName();
				int stars = jsonReader.nextInt();

				// date
				jsonReader.nextName();
				jsonReader.nextString();

				// text String
				jsonReader.nextName();

				// Write the review text inside a text file

				String text = jsonReader.nextString();

				// removing stops words and special characters
				String formattedText = removeStopWordsAndSpecial(text);

				//Segregating content as per the number of stars
				if (stars == 1) {

					star1.append(formattedText);
					star1.append("\n");

				} else if (stars == 2) {

					star2.append(formattedText);
					star2.append("\n");

				} else if (stars == 3) {

					star3.append(formattedText);
					star3.append("\n");

				} else if (stars == 4) {

					star4.append(formattedText);
					star4.append("\n");

				} else if (stars == 5) {

					star5.append(formattedText);
					star5.append("\n");
				}

				// type String
				jsonReader.nextName();
				jsonReader.nextString();

				// business_id String
				jsonReader.nextName();
				jsonReader.nextString();

				jsonReader.endObject();

			}

			// write to files

			// stars 1
			if(star1.length() != 0){
			writer = new FileWriter(new File(filepath + file1 + ".txt"));
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(star1.toString());
			bufferedWriter.close();
			writer.close();
            }
            
			// stars 2
            if(star2.length() != 0){
			writer = new FileWriter(new File(filepath + file2 + ".txt"));
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(star2.toString());
			bufferedWriter.close();
			writer.close();
            }
            
			// stars 3
            if(star3.length() != 0 ){
			writer = new FileWriter(new File(filepath + file3 + ".txt"));
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(star3.toString());
			bufferedWriter.close();
			writer.close();
            }
            
			// stars 4
            if(star4.length() != 0 ){
			writer = new FileWriter(new File(filepath + file4 + ".txt"));
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(star4.toString());
			bufferedWriter.close();
			writer.close();
            }
            
			// stars 5
            if(star5.length() != 0 ){
			writer = new FileWriter(new File(filepath + file5 + ".txt"));
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(star5.toString());
			bufferedWriter.close();
			writer.close();
            }
            
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (jsonReader != null) {
				try {
					jsonReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
