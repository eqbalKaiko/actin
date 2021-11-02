package com.hartwig.actin.treatment;

import java.io.IOException;
import java.util.List;

import com.hartwig.actin.treatment.datamodel.Trial;
import com.hartwig.actin.treatment.serialization.TrialJson;
import com.hartwig.actin.treatment.trial.TrialFactory;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class TreatmentCreatorApplication {

    private static final Logger LOGGER = LogManager.getLogger(TreatmentCreatorApplication.class);

    private static final String APPLICATION = "ACTIN Treatment Creator";
    private static final String VERSION = TreatmentCreatorApplication.class.getPackage().getImplementationVersion();

    public static void main(@NotNull String... args) throws IOException {
        LOGGER.info("Running {} v{}", APPLICATION, VERSION);

        Options options = TreatmentCreatorConfig.createOptions();

        TreatmentCreatorConfig config = null;
        try {
            config = TreatmentCreatorConfig.createConfig(new DefaultParser().parse(options, args));
        } catch (ParseException exception) {
            LOGGER.warn(exception);
            new HelpFormatter().printHelp(APPLICATION, options);
            System.exit(1);
        }

        new TreatmentCreatorApplication(config).run();
    }

    @NotNull
    private final TreatmentCreatorConfig config;

    public TreatmentCreatorApplication(@NotNull final TreatmentCreatorConfig config) {
        this.config = config;
    }

    public void run() throws IOException {
        LOGGER.info("Creating trial database");
        List<Trial> trials = TrialFactory.fromTrialConfigDirectory(config.trialConfigDirectory());

        String outputDirectory = config.outputDirectory();
        LOGGER.info("Writing {} trials to {}", trials.size(), outputDirectory);
        TrialJson.write(trials, outputDirectory);

        LOGGER.info("Done!");
    }
}