package io.github.ridiekel.jeletask.client.builder.composer.config.statecalculator;

import io.github.ridiekel.jeletask.client.builder.composer.config.NumberConverter;
import io.github.ridiekel.jeletask.client.spec.ComponentSpec;
import io.github.ridiekel.jeletask.client.spec.state.ComponentState;

public class TemperatureStateCalculator extends SimpleStateCalculator {
    private final double divide;
    private final int subtract;

    public TemperatureStateCalculator(NumberConverter numberConverter, int divide, int subtract) {
        super(numberConverter);
        this.divide = divide;
        this.subtract = subtract;
    }

    @Override
    public ComponentState convertGet(byte[] dataBytes) {
        long base = this.getNumberConverter().convert(dataBytes).longValue();
        double divided = base / this.divide;
        double subtracted = divided - this.subtract;
        return new ComponentState(subtracted);
    }

    @Override
    public byte[] convertSetState(ComponentState value) {
        long base = Long.parseLong(value.getState());
        long added = base + this.subtract;
        double multiplied = added * this.divide;
        return this.getNumberConverter().convert(Math.round(multiplied));
    }

    @Override
    public ComponentState getDefaultState(ComponentSpec component) {
        return new ComponentState("18");
    }
}
