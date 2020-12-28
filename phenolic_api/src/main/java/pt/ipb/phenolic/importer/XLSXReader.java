package pt.ipb.phenolic.importer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pt.ipb.phenolic.models.Molecule;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Source;

import java.io.IOException;
import java.io.InputStream;

@Service
public class XLSXReader {

    public void read(InputStream inputStream) throws IOException {
        System.out.println(inputStream);

        var workbook = new XSSFWorkbook(inputStream);
        System.out.println("getNumberOfSheets: " + workbook.getNumberOfSheets());
        System.out.println("getNumberOfNames: " + workbook.getNumberOfNames());

        workbook.forEach(sheet -> {
            var source = new Source();
            source.setName(sheet.getSheetName());

            System.out.println("sourceName: " + source.getName());

            sheet.forEach(row -> {
                // Ignore header rows in the sheet
                if (row.getRowNum() > 1) {
                    Cell cell = row.getCell(2);
                    if (cell != null) {
                        var moleculeName = cell.getStringCellValue();
                        System.out.println("moleculeName: " + moleculeName);

                        var x = cell.getColumnIndex();
                        var y = cell.getRowIndex();

                        Phenolic parent = null;

                        Cell parentCell = getParentOf(sheet, x, y);
                        if (parentCell != null) {
                            Phenolic grandParent = null;

                            Cell grandParentCell = getParentOf(sheet, (x - 1), y);
                            if (grandParentCell != null) {
                                grandParent = new Phenolic(null, grandParentCell.getStringCellValue());
                            }

                            parent = new Phenolic(grandParent, parentCell.getStringCellValue());
                        }

                        if (parent != null) {
                            var molecule = new Molecule(moleculeName);

                            // TODO: lambdas handler
                            // Cell lambdas = row.getCell(3);

                            var espectroUVCell = row.getCell(4);
                            if (espectroUVCell.getStringCellValue() != null)
                                molecule.setEspectroUV((int) espectroUVCell.getNumericCellValue());

                            var weightCell = row.getCell(5);
                            molecule.setWeight((int) weightCell.getNumericCellValue());

                            var ionMPLusCell = row.getCell(6);
                            molecule.setIonMPlus((int) ionMPLusCell.getNumericCellValue());

                            var pseudoMHPlusCell = row.getCell(7);
                            molecule.setPseudoMHPlus((int) pseudoMHPlusCell.getNumericCellValue());

                            var pseudoMHMinusCell = row.getCell(8);
                            molecule.setPseudoMHMinus((int) pseudoMHMinusCell.getNumericCellValue());

                            var pseudoTwoMHPlusCell = row.getCell(9);
                            molecule.setPseudoTwoMHPlus((int) pseudoTwoMHPlusCell.getNumericCellValue());

                            var pseudoTwoMHMinusCell = row.getCell(10);
                            molecule.setPseudoTwoMHMinus((int) pseudoTwoMHMinusCell.getNumericCellValue());

                            var multiChargedProductsMHTwoMinusCell = row.getCell(11);
                            molecule.setMultiChargedProductsMHTwoMinus((int) multiChargedProductsMHTwoMinusCell.getNumericCellValue());

                            var multiChargedProductsMHThreeMinusCell = row.getCell(12);
                            molecule.setMultiChargedProductsMHThreeMinus((int) multiChargedProductsMHThreeMinusCell.getNumericCellValue());

                            var exactHighResolutionCell = row.getCell(13);
                            molecule.setExactHighResolution((int) exactHighResolutionCell.getNumericCellValue());

                            var aductsCell = row.getCell(14);
                            molecule.setAducts((int) aductsCell.getNumericCellValue());

                            var equipmentsCell = row.getCell(23);
                            molecule.setEquipment(equipmentsCell.getStringCellValue());

                            var methodologyCell = row.getCell(24);
                            molecule.setMethodology(methodologyCell.getStringCellValue());

                            var ionizationMethodology = row.getCell(25);
                            molecule.setIonizationMethodology(ionizationMethodology.getStringCellValue());

                            var varietyCell = row.getCell(26);
                            molecule.setVariety(varietyCell.getStringCellValue());

                            var sampleOriginCell = row.getCell(27);
                            molecule.setSampleOrigin(sampleOriginCell.getStringCellValue());

                            var seasonOfCollectionCell = row.getCell(28);
                            molecule.setSeasonOfCollection(seasonOfCollectionCell.getStringCellValue());

                            var plantPartCell = row.getCell(29);
                            molecule.setPlantPart(plantPartCell.getStringCellValue());

                            var referenceCell = row.getCell(30);
                            if (referenceCell != null)
                                molecule.setReference(referenceCell.getStringCellValue());

                            System.out.println("molecule: " + molecule);
                        }
                    }
                }
            });
        });
    }

    private Cell getParentOf(Sheet sheet, int x, int y) {
        for (CellRangeAddress addr : sheet.getMergedRegions()) {
            CellAddress address = new CellAddress(y, x - 1);
            if (addr.isInRange(address)) {
                Row row = sheet.getRow(addr.getFirstRow());
                return row.getCell(addr.getFirstColumn());
            }
        }
        return null;
    }
}
