
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * You are given an XML file with one line written per entity.
 * 
 * A valid XML entity is of the following format - <!ENTITY name value> where name is a string and value can either be an integer in the 
 * range 1 to 10^5 or combination of other entities joined by ; 
 * 
 * e.g. 
 * <!ENTITY key1 value1>
 * <!ENTITY key2 key1;key1>
 * 
 * The entity defined in terms of other entities will be expanded to understand the meaning. For example, the above example will be expanded as
 * <!ENTITY key1 value1>
 * <!ENTITY key2 value1>
 * <!ENTITY key2 value1>
 * 
 * Assume you are to find out whether a given XML is valid or not. If it is valid, find out whether the number of lines in the expanded file
 * are less than or equal to given threshold(MAX_ALLOWED_LINE_COUNT).
 * 
 * Output :
 * --------
 * If the XML is not in valid XML format --> print 0
 * If the XML is valid
 * 		a. the number of lines in expanded file is <= MAX_ALLOWED_LINE_COUNT --> print 1[ SPACE ]numberOfLinesInDocument
 * 		b. the number of lines in expanded file > MAX_ALLOWED_LINE_COUNT --> print 0[ SPACE ]numberOfLinesInDocument
 * </pre>
 * @author rebecca
 */
public class XMLEntityExpansion {
	public static void entityExpansion(long l, List<String> entities) {
		Map<String, Entity> parsedEntities = new HashMap<>();
		int documentLineCount = 0;
		boolean isDocumentProper = true;

		for (String entity : entities) {
			Entity parsedEntity = parseEntity(entity, parsedEntities);
			if (parsedEntity == null) {
				isDocumentProper = false;
				break;
			}
			parsedEntities.put(parsedEntity.getKey(), parsedEntity);
		}

		for (Entity entity : parsedEntities.values()) {
			if (entity.isBasicEntity()) {
				documentLineCount += 1;
				entity.setNumberOfLines(1);
			} else {
				for (String usedEntity : entity.getValues()) {
					Entity entity2 = parsedEntities.get(usedEntity);
					if (entity2.getNumberOfLines() == -1) {
						documentLineCount += getNumberOfLines(entity2, parsedEntities);
					} else {
						documentLineCount += entity2.getNumberOfLines();
					}
				}
			}
		}

		if (!isDocumentProper) {
			System.out.println("0");
		} else {
			System.out.println((documentLineCount <= l ? "1" : "0") + " " + documentLineCount);
		}
	}

	private static int getNumberOfLines(Entity entity, Map<String, Entity> entityMap) {
		if (entity.isBasicEntity()) {
			entity.setNumberOfLines(1);
			return 1;
		}
		int numberOfLine = 0;
		for (String entityUsed : entity.getValues()) {
			Entity entity2 = entityMap.get(entityUsed);
			if (entity2.getNumberOfLines() == -1) {
				numberOfLine += getNumberOfLines(entity2, entityMap);
			} else {
				numberOfLine += entity2.getNumberOfLines();
			}
		}
		entity.setNumberOfLines(numberOfLine);
		return numberOfLine;
	}

	private static class Entity {
		private final String key;

		private int numberOfLines = -1;

		private List<String> values;

		private int intValue = 0;

		private boolean isBasicEntity = true;

		public Entity(String key, List<String> values) {
			super();
			this.key = key;
			this.values = values;
			this.isBasicEntity = false;
		}

		public Entity(String key, int value) {
			this.key = key;
			this.intValue = value;
			this.isBasicEntity = true;
		}

		/**
		 * @return the intValue
		 */
		public int getIntValue() {
			return intValue;
		}

		/**
		 * @return the isBasicEntity
		 */
		public boolean isBasicEntity() {
			return isBasicEntity;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @return the values
		 */
		public List<String> getValues() {
			return values;
		}

		/**
		 * @param numberOfLines
		 *            the numberOfLines to set
		 */
		public void setNumberOfLines(int numberOfLines) {
			this.numberOfLines = numberOfLines;
		}

		/**
		 * @return the numberOfLines
		 */
		public int getNumberOfLines() {
			return numberOfLines;
		}
	}

	private static final String ENTITY_PREFIX = "<!ENTITY ";
	private static final String ENTITY_SUFFIX = ">";
	private static final String MULTI_VALUED_FIELD_SEPARATOR = ";";

	private static Entity parseEntity(String line, Map<String, Entity> entityMap) {
		// format
		if (!line.startsWith(ENTITY_PREFIX) || !line.endsWith(ENTITY_SUFFIX))
			return null;

		String[] fields = line.split(" +", -1);
		String name = fields[1];
		String[] valueComponents = (fields[2].substring(0, fields[2].length() - ENTITY_SUFFIX.length()))
				.split(MULTI_VALUED_FIELD_SEPARATOR, -1);

		Entity entity = null;
		// only one entry --> either value or using other
		if (valueComponents.length == 1) {
			try {
				Integer.parseInt(valueComponents[0]);
				entity = new Entity(name, 1);// integer value
			} catch (NumberFormatException e) {
				entity = new Entity(name, Arrays.asList(valueComponents[0]));
			}
		} else {
			entity = new Entity(name, Arrays.asList(valueComponents));
		}

		return entity;
	}

	public static void main(String[] args) {
		List<String> entities = new ArrayList<>();
		entities.add("<!ENTITY a0 a1;a2>");
		entities.add("<!ENTITY a1 a2;a2>");
		entities.add("<!ENTITY a2 11888>");
		int l = 1;

		entityExpansion(l, entities);
	}

}
