# Domain Model Associations

| **_Concept A_**   | **_Association_**  | **_Concept B_**                                            |                                       
|:------------------------|:-----------------|:--------------------------------------------|
| **Company** | owns | Laboratory |
|  | performs | Test |
|  | conducts | TestType |
| **ChemicalLaboratory** | performs | Analysis |
|  | is a | Laboratory |
| **ClinicalAnalysislaboratory** | is a | Laboratory |
| **Test** | requested by | Client |
|  | is of | TestType |
|  | registered by | Receptionist |
|  | performed by | MedicalLabTechnician |
|  | contains | Sample
| **Sample** | collected by | MedicalLabTechnician |
|  | is contained in | Test |
|  | collected at | ClinicalAnalysisLaboratory |
| **ChemicalAnalysis** | performed by | ClinicalChemistryTechnologist |
|  | performed at | ChemicalLaboratory |
|  | results in | AnalysisResult |
| **AnalysisResult** | validated by | SpecialistDoctor |
|  |  | LaboratoryCoordinator |
|  | used for | Diagnosis |
| **Diagnosis** | contained in | Report |
|  | done by | SpecialistDoctor |
|  | checked by | LaboratoryCoordinator |
| **Parameter** | presented under | Category |
|  | requested by | Test |
| **Category** | created by | Administrator |
| **Receptionist** | is part of | Staff |
|  | registers | Client |
|  | registers | Test |
| **MedicalLabTechnician** | is part of | Staff |
|  | collects | Sample |
|  | registers | Sample |
| **ClinicalChemistryTechnologist** | is part of | Staff |
|  | performs | Analysis |
|  | records | AnalysisResult |
| **SpecialistDoctor** | is part of | Staff |
|  | makes | Diagnosis |
|  | writes | Report |
| **LaboratoryCoordinator** | is part of | Staff |
|  | validates | AnalysisResult |
|  |  | Diagnosis |
| **Administrator** | manages | Staff |
|  | is part of | Staff |
|  | creates | Category |
|  |  | TestType |
|  |  | Parameter |
| **Client** | registered by | Receptionist |
|  | requests | Test |
|  | uses | LabOrder |
| **LabOrder** | prescribed by | Doctor |
| **CovidReport** | uses | AnalysisResult |
|  | requested by | NHS |






