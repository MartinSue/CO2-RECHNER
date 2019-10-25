CREATE TABLE berechnungen (
    name TEXT NOT NULL,
    kraftstoff TEXT NOT NULL,
    verbrauch INTEGER NOT NULL,
    strecke DOUBLE NOT NULL,
    ergebnisSpezifisch DOUBLE NOT NULL,
    ergebnisAbsolut DOUBLE NOT NULL
);

CREATE INDEX mein_index ON berechnungen (name);



