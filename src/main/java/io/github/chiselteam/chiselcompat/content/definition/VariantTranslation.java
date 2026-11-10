package io.github.chiselteam.chiselcompat.content.definition;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public record VariantTranslation(String blockName, String description, boolean waxed) {
}
