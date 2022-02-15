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
|  | is part of | Test |
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
|  | is a | User |
| **MedicalLabTechnician** | is part of | Staff |
|  | collects | Sample |
|  | registers | Sample |
|  | is a | User |
| **ClinicalChemistryTechnologist** | is part of | Staff |
|  | performs | ChemicalAnalysis |
|  | records | AnalysisResult |
|  | is a | User |
| **SpecialistDoctor** | is part of | Staff |
|  | makes | Diagnosis |
|  | writes | Report |
|  | is a | User |
| **LaboratoryCoordinator** | is part of | Staff |
|  | validates | AnalysisResult |
|  |  | Diagnosis |
|  | is a | User |
| **Administrator** | manages | Staff |
|  | is part of | Staff |
|  | creates | Category |
|  |  | TestType |
|  |  | Parameter |
|  | is a | User |
| **Client** | registered by | Receptionist |
|  | requests | Test |
|  | uses | LabOrder |
|  | uses | LabOrder |
|  | is a | User |
| **LabOrder** | prescribed by | Doctor |
| **CovidReport** | uses | AnalysisResult |
|  | requested by | NHS |





