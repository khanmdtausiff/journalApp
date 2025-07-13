package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.params.provider.ArgumentsProvider;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("ededede").password("shyam").build()),
                Arguments.of(User.builder().username("ededed").password("").build())
        );
    }
}
