package io.github.ridiekel.jeletask.mqtt.listener.homeassistant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HAConfig<T extends HAConfig<T>> {
    private static final Pattern INVALID_CHARS = Pattern.compile("[^a-zA-Z0-9_-]");
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ObjectNode config;
    private final ObjectNode device;
    private final ArrayNode deviceIdentifiers;

    public HAConfig(HAConfigParameters parameters) {
        this.config = OBJECT_MAPPER.createObjectNode();
        this.device = this.config.putObject("device");

        this.deviceIdentifiers = this.device.putArray("identifiers");

        var uniqueId = id(parameters);

        this.baseTopic(parameters.getBaseTopic())
                .stateTopic("~/state")
                .uniqueId(uniqueId)
                .objectId(uniqueId)
                .name(parameters.getComponentSpec().getDescription())
                .manufacturer("teletask")
                .deviceIdentifier(parameters.getIdentifier())
                .deviceName(String.format("teletask-%s", parameters.getIdentifier()))
                .model(parameters.getCentralUnit().getCentralUnitType().getDisplayName());
    }

    public T baseTopic(String value) {
        return this.put("~", value);
    }

    public T deviceIdentifier(String identifier) {
        return this.put(this.deviceIdentifiers, identifier);
    }

    public T deviceName(String name) {
        return this.putDeviceProperty("name", removeInvalid(name, "."));
    }

    public T manufacturer(String value) {
        return this.putDeviceProperty("manufacturer", value);
    }

    public T model(String value) {
        return this.putDeviceProperty("model", value);
    }

    public T uniqueId(String value) {
        return this.put("unique_id", value);
    }

    public T objectId(String value) {
        return this.put("object_id", value);
    }

    public T name(String value) {
        return this.put("name", value);
    }

    public T stateTopic(String value) {
        return this.put("state_topic", value);
    }

    private T putDeviceProperty(String key, String value) {
        return this.put(this.device, key, value);
    }

    public T put(String key, String value) {
        return this.put(this.config, key, value);
    }

    private T put(ObjectNode node, String key, String value) {
        node.put(key, value);
        return this.self();
    }

    public String getStringValue(String key) {
        return this.config.get(key).asText();
    }

    public T putBoolean(String key, boolean value) {
        return this.putBoolean(this.config, key, value);
    }

    private T putBoolean(ObjectNode node, String key, boolean value) {
        node.put(key, value);
        return this.self();
    }

    public T putDouble(String key, Double value) {
        return this.putDouble(this.config, key, value);
    }

    private T putDouble(ObjectNode node, String key, Double value) {
        node.put(key, value);
        return this.self();
    }


    public T putInt(String key, int value) {
        return this.putInt(this.config, key, value);
    }

    private T putInt(ObjectNode node, String key, int value) {
        node.put(key, value);
        return this.self();
    }

    public T putArray(String key, String... value) {
        return this.putArray(this.config, key, value);
    }

    private T putArray(ObjectNode node, String key, String... value) {
        ArrayNode array = node.putArray(key);
        Stream.of(value).forEach(array::add);
        return this.self();
    }

    private T put(ArrayNode node, String value) {
        node.add(value);
        return this.self();
    }

    protected String id(HAConfigParameters parameters) {
        String id = "teletask-" + parameters.getIdentifier() + "-" + parameters.getComponentSpec().getFunction().toString().toLowerCase() + "-" + parameters.getComponentSpec().getNumber();
        return removeInvalid(id, "_");
    }

    private static String removeInvalid(String value, String replacement) {
        return INVALID_CHARS.matcher(value).replaceAll(replacement);
    }

    @SuppressWarnings("unchecked")
    public T self() {
        return (T) this;
    }

    @Override
    public String toString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this.config);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
