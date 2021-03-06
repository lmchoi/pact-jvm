package au.com.dius.pact.consumer;

import java.util.HashMap;
import java.util.Map;

public abstract class DslPart {
    protected final DslPart parent;
    protected final String root;
    protected Map<String, Object> matchers = new HashMap<String, Object>();

    public DslPart(DslPart parent, String root) {
        this.parent = parent;
        this.root = root;
    }

    public DslPart(String root) {
        this.parent = null;
        this.root = root;
    }

    protected abstract void putObject(DslPart object);
    protected abstract void putArray(DslPart object);
    protected abstract Object getBody();

    /**
     * Field which is an array
     * @param name field name
     */
    public abstract PactDslJsonArray array(String name);

    /**
     * Element as an array
     */
    public abstract PactDslJsonArray array();

    /**
     * Close of the previous array element
     */
    public abstract DslPart closeArray();

    /**
     * Array field where each element must match the following object
     * @param name field name
     */
    public abstract PactDslJsonBody arrayLike(String name);

    /**
     * Array element where each element of the array must match the following object
     */
    public abstract PactDslJsonBody arrayLike();

    /**
     * Array field with a minumum size and each element must match the provided object
     * @param name field name
     * @param size minimum size
     */
    public abstract PactDslJsonBody minArrayLike(String name, Integer size);

    /**
     * Array element with a minumum size and each element of the array must match the provided object
     * @param size minimum size
     */
    public abstract PactDslJsonBody minArrayLike(Integer size);

    /**
     * Array field with a maximum size and each element must match the provided object
     * @param name field name
     * @param size maximum size
     */
    public abstract PactDslJsonBody maxArrayLike(String name, Integer size);

    /**
     * Array element with a maximum size and each element of the array must match the provided object
     * @param size minimum size
     */
    public abstract PactDslJsonBody maxArrayLike(Integer size);

    /**
     * Object field
     * @param name field name
     */
    public abstract PactDslJsonBody object(String name);

    /**
     * Object element
     */
    public abstract PactDslJsonBody object();

    /**
     * Close off the previous object
     * @return
     */
    public abstract DslPart closeObject();

    public Map<String, Object> getMatchers() {
        return matchers;
    }

    public void setMatchers(Map<String, Object> matchers) {
        this.matchers = matchers;
    }

    protected Map<String, Object> matchType() {
        return matchType("type");
    }

    protected Map<String, Object> matchType(String type) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("match", type);
        return jsonObject;
    }

    protected Map<String, Object> regexp(String regex) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("regex", regex);
        return jsonObject;
    }

    protected Map<String, Object> matchTimestamp(String format) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("timestamp", format);
        return jsonObject;
    }

    protected Map<String, Object> matchDate(String format) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("date", format);
        return jsonObject;
    }

    protected Map<String, Object> matchTime(String format) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("time", format);
        return jsonObject;
    }

    protected Map<String, Object> matchMin(Integer min) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("min", min);
        return jsonObject;
    }

    protected Map<String, Object> matchMax(Integer max) {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("min", max);
        return jsonObject;
    }

    public PactDslJsonBody asBody() {
        return (PactDslJsonBody) this;
    }

    public PactDslJsonArray asArray() {
        return (PactDslJsonArray) this;
    }
}
